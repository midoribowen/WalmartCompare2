package com.walmart.walmartcompare.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.walmart.walmartcompare.model.SearchItem

/**
 * Database schema that holds the list of items.
 */
@Database(
        entities = [SearchItem::class],
        version = 1,
        exportSchema = false
)
abstract class SearchItemDatabase : RoomDatabase() {
    abstract fun searchItemsDao(): SearchItemDao

    companion object {
        @Volatile
        private var INSTANCE: SearchItemDatabase? = null

        fun getInstance(context: Context): SearchItemDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        SearchItemDatabase::class.java, "Walmart.db")
                        .build()
    }
}
