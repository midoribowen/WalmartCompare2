package com.walmart.walmartcompare.db

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.walmart.walmartcompare.model.SearchItem

/**
 * Room data access object for accessing the SearchItem table.
 */
@Dao
interface SearchItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<SearchItem>)

    @Query("SELECT * FROM searchItems WHERE (name LIKE :queryString) OR (shortDescription LIKE " +
            ":queryString) ORDER BY numReviews DESC, name ASC")
    fun searchResultByQuery(queryString: String): DataSource.Factory<Int, SearchItem>

    // Set up for testing, needs to change to use DataSource.Factory paged list
    @Query("SELECT * FROM searchItems")
    fun getSearchResults(): LiveData<List<SearchItem>>
}
