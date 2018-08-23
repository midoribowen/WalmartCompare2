package com.walmart.walmartcompare.ui

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.walmart.walmartcompare.R
import com.walmart.walmartcompare.model.SearchItem

class SearchItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.item_name)

    private var searchItem: SearchItem? = null

    init {
        view.setOnClickListener {
            searchItem?.productUrl?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(searchItemRVItem: SearchItem?) {
        if (searchItemRVItem == null) {
            val resources = itemView.resources
            name.text == "Loading"
        } else {
            showSearchItemData(searchItemRVItem)
        }
    }

    private fun showSearchItemData(searchItem: SearchItem) {
        this.searchItem = searchItem
        name.text = searchItem.name
    }

    companion object {
        fun create(parent: ViewGroup): SearchItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout
                    .search_item_view_item, parent, false)
            return SearchItemViewHolder(view)
        }
    }
}
