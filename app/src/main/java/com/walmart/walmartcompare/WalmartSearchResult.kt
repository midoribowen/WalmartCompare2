package com.walmart.walmartcompare

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * WalmartSearchResult from a search, which contains LiveData<List<SearchItem>> holding query data,
 * and a LiveData<String> of network error state.
 */
data class WalmartSearchResult(
        val data: LiveData<PagedList<SearchItem>>,
        val networkErrors: LiveData<String>)
