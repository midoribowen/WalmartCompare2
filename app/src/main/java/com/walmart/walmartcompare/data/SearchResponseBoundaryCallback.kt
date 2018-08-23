package com.walmart.walmartcompare.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.util.Log
import com.walmart.walmartcompare.db.WalmartLocalCache
import com.walmart.walmartcompare.api.WalmartService
import com.walmart.walmartcompare.model.SearchItem
import com.walmart.walmartcompare.api.searchItems

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
        private const val NETWORK_PAGE_SIZE = 25
    }

    // keep the last requested page. When the request is successful, increment the page number.
    // TODO: Fix this to use item to start and increment up by page size
    private var lastRequestedPageStart = 1

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
        Log.d(TAG, "lastRequestedPage=" + lastRequestedPageStart)
        searchItems(service, query, lastRequestedPageStart, NETWORK_PAGE_SIZE, { items ->
                    cache.insert(items, {
                        lastRequestedPageStart += NETWORK_PAGE_SIZE
                        isRequestInProgress = false
                    })
                }, { error ->
            _networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}
