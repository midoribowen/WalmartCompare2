package com.walmart.walmartcompare

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

private val TAG = ProductListActivity::class.java.simpleName

class ProductListActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchItemsViewModel
//    private val adapter = SearchItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // get the view model
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
                .get(SearchItemsViewModel::class.java)

        // initialize adapter
        initAdapter()

        val query = "bench"
        viewModel.searchItems(query)
        initSearch(query)

    }

    private fun initAdapter() {
        viewModel.searchItems.observe(this, Observer<PagedList<SearchItem>> {
            Log.d(TAG, "list size = ${it?.size}")
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "Oops! ${it}", Toast.LENGTH_LONG).show()
        })
    }

    private fun initSearch(query: String) {
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
        // listeners for key up
    }
}
