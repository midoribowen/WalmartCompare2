package com.walmart.walmartcompare

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import java.util.concurrent.Executors

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {
    /**
     * Creates an instance of [WalmartLocalCache] based on the database DAO.
     */
    private fun provideCache(context: Context): WalmartLocalCache {
        val database = SearchItemDatabase.getInstance(context)
        return WalmartLocalCache(database.searchItemsDao(), Executors.newSingleThreadExecutor())
    }

    /**
     * Creates an instance of [WalmartRepository] based on the [WalmartService] and a
     * [WalmartLocalCache]
     */
    private fun provideWalmartRepository(context: Context): WalmartRepository {
        return WalmartRepository(WalmartService.create(), provideCache(context))
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideWalmartRepository(context))
    }
}