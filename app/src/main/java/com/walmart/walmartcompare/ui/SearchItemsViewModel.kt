package com.walmart.walmartcompare.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.util.Log
import com.walmart.walmartcompare.data.WalmartRepository
import com.walmart.walmartcompare.model.SearchItem
import com.walmart.walmartcompare.model.WalmartSearchResult

private val TAG = WalmartRepository::class.java.simpleName

class SearchItemsViewModel(private val repository: WalmartRepository) : ViewModel() {
    private val queryLiveData = MutableLiveData<String>()
    private val searchItemResult: LiveData<WalmartSearchResult> = Transformations.map(queryLiveData, {
      repository.search(it)
    })

    val searchItems: LiveData<PagedList<SearchItem>> = Transformations.switchMap(searchItemResult,
            { it -> it.data })
    val networkErrors: LiveData<String> = Transformations.switchMap(searchItemResult,
            { it -> it.networkErrors })

    /**
     * Search for items based on a query string.
     */
    fun searchItems(queryString: String) {
        queryLiveData.postValue(queryString)
    }

    /**
     * Get the last query value
     */
    fun lastQueryValue(): String? = queryLiveData.value
}

