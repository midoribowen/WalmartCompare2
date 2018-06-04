package com.walmart.walmartcompare

import android.arch.paging.LivePagedListBuilder
import android.util.Log

private val TAG = WalmartRepository::class.java.simpleName

class WalmartRepository(
        private val service: WalmartService,
        private val cache: WalmartLocalCache
) {
    /**
     * Search repositories whose names match the query.
     */
    fun search(query: String): WalmartSearchResult {
        Log.d(TAG, "New query: $query")

        // Get data source factory from the local cache
        val dataSourceFactory = cache.searchResultByQuery(query)

        // every new query creates a new BoundaryCallback
        // The BoundaryCallback will observe when the user reaches to the edges of
        // the list and update the database with extra data
        val boundaryCallback = SearchResponseBoundaryCallback(query, service, cache)
        val networkErrors = boundaryCallback.networkErrors

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)
                .build()

        // Get the network errors exposed by the boundary callback
        return WalmartSearchResult(data, networkErrors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }
}