package com.walmart.walmartcompare.db

import android.arch.paging.DataSource
import android.util.Log
import com.walmart.walmartcompare.model.SearchItem
import java.util.concurrent.Executor

private val TAG = WalmartLocalCache::class.java.simpleName

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class WalmartLocalCache(
        private val searchItemDao: SearchItemDao,
        private val ioExecutor: Executor
) {
    /**
     * Insert a list of items in the database, on a background thread.
     */
    fun insert(searchItems: List<SearchItem>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            Log.d(TAG, "inserting ${searchItems.size} searchItems")
            searchItemDao.insert(searchItems)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<SearchItem>> from the Dao, based on search query
     */
    fun searchResultByQuery(query: String): DataSource.Factory<Int, SearchItem> {
        // appending '%' so we can allow other characters to be before and after the query string
        val newQuery = "%${query.replace(' ', '%')}%"
        return searchItemDao.searchResultByQuery(newQuery)
    }
}