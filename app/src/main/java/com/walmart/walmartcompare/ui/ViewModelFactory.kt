package com.walmart.walmartcompare.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.walmart.walmartcompare.data.WalmartRepository

class ViewModelFactory(private val repository: WalmartRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchItemsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchItemsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
