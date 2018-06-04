package com.walmart.walmartcompare

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.util.Log

private val TAG = SearchResponseBoundaryCallback::class.java.simpleName
/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class SearchResponseBoundaryCallback(
        private val query: String,
        private val service: WalmartService,
        private val cache: WalmartLocalCache
) : PagedList.BoundaryCallback<SearchItem>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    // keep the last requested page. When the request is successful, increment the page number.
    // TODO: Fix this to use item to start and increment up by page size
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()
    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        Log.d(TAG, "onZeroItemsLoaded")
        requestAndSaveData(query)
    }

    override fun onItemAtEndLoaded(itemAtEnd: SearchItem) {
        Log.d(TAG, "onItemAtEndLoaded")
        requestAndSaveData(query)
    }

    private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return
        isRequestInProgress = true
        searchItems(service, query,
//                lastRequestedPage,
                { items ->
            cache.insert(items, {
//                lastRequestedPage++
                isRequestInProgress = false
            })
        }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}
