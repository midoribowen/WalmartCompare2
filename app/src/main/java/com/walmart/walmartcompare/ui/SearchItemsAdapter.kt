package com.walmart.walmartcompare.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.walmart.walmartcompare.model.SearchItem

/**
 * Created by Midori on 8/20/18
 */
class SearchItemsAdapter : PagedListAdapter<SearchItem, RecyclerView.ViewHolder>(SEARCH_ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchItemViewHolder.create(parent);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val searchItemRVItem = getItem(position);
        if (searchItemRVItem != null) {
            (holder as SearchItemViewHolder).bind(searchItemRVItem)
        }
    }

    companion object {
        private val SEARCH_ITEM_COMPARATOR = object : DiffUtil.ItemCallback<SearchItem>() {
            override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
                oldItem.itemId == newItem.itemId

            override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean =
                oldItem == newItem
        }
    }
}