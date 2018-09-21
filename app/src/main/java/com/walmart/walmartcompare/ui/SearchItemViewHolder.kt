package com.walmart.walmartcompare.ui

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.walmart.walmartcompare.R
import com.walmart.walmartcompare.model.SearchItem
import com.walmart.walmartcompare.Constants.Companion.ITEM_IMAGE_URL
import com.walmart.walmartcompare.Constants.Companion.ITEM_LONG_DESC
import com.walmart.walmartcompare.Constants.Companion.ITEM_NAME
import com.walmart.walmartcompare.Constants.Companion.ITEM_PRICE
import com.walmart.walmartcompare.Constants.Companion.ITEM_RATING
import com.walmart.walmartcompare.Constants.Companion.ITEM_REVIEW_COUNT
import com.walmart.walmartcompare.Constants.Companion.ITEM_SHORT_DESC

class SearchItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mName: TextView = view.findViewById(R.id.item_name)
    private val mThumbnailImage: ImageView = view.findViewById(R.id.item_image_view)
    private val mProductPrice: TextView = view.findViewById(R.id.item_price)
    private val mRatingBar: RatingBar = view.findViewById(R.id.item_rating_bar)
    private val mReviewCount: TextView = view.findViewById(R.id.item_review_count)

    private var searchItem: SearchItem? = null

    protected var requestManager : RequestManager = Glide.with(itemView.context);

    init {
        view.setOnClickListener {
            val productDetailIntent = Intent(it.context, ProductDetailActivity::class.java).apply {
                putExtra(ITEM_IMAGE_URL, searchItem?.mediumImage)
                putExtra(ITEM_NAME, searchItem?.name)
                putExtra(ITEM_PRICE, searchItem?.salePrice)
                putExtra(ITEM_RATING, searchItem?.customerRating)
                putExtra(ITEM_REVIEW_COUNT, searchItem?.numReviews)
                putExtra(ITEM_SHORT_DESC, searchItem?.shortDescription)
                putExtra(ITEM_LONG_DESC, searchItem?.longDescription)
            }
            it.context.startActivity(productDetailIntent)
        }
    }

    fun bind(searchItemRVItem: SearchItem?) {
        if (searchItemRVItem == null) {
            val resources = itemView.resources
            mName.text == resources.getString(R.string.loading_text)
        } else {
            showSearchItemData(searchItemRVItem)
        }
    }

    private fun showSearchItemData(searchItem: SearchItem) {
        this.searchItem = searchItem
        val resources = itemView.resources

        val requestOptions : RequestOptions = RequestOptions().centerCrop()
        if (searchItem.thumbnailImage != null) {
            val imageUrl = searchItem.thumbnailImage
            requestOptions
                    .placeholder(R.drawable.placeholder)
                    .fallback(R.drawable.placeholder)
                    .error(R.drawable.outline_broken_image_24)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            requestManager
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(mThumbnailImage)
        } else {
            requestManager
                    .load(R.drawable.outline_photo_24)
                    .apply(requestOptions)
                    .into(mThumbnailImage)
        }
        mName.text = searchItem.name
        mProductPrice.text = resources.getString(R.string.product_price, searchItem.salePrice)
        // Elvis operator: if searchItem.customerRating is null then reviewRating = 0f
        val reviewRating: Float = searchItem.customerRating?.toFloat() ?: 0f
        mRatingBar.rating = reviewRating
        mRatingBar.contentDescription = resources.getString(R.string
                .rating_bar_content_description, reviewRating)
        mReviewCount.text = resources.getString(R.string.review_count_text, searchItem.numReviews)
        mReviewCount.contentDescription = resources.getString(R.string
                .review_count_content_description, searchItem.numReviews)

    }

    companion object {
        fun create(parent: ViewGroup): SearchItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout
                    .search_item_view_item, parent, false)
            return SearchItemViewHolder(view)
        }
    }
}
