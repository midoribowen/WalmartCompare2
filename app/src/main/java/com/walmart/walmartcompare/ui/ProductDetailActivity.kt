package com.walmart.walmartcompare.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.walmart.walmartcompare.Constants.Companion.ITEM_IMAGE_URL
import com.walmart.walmartcompare.Constants.Companion.ITEM_LONG_DESC
import com.walmart.walmartcompare.Constants.Companion.ITEM_NAME
import com.walmart.walmartcompare.Constants.Companion.ITEM_PRICE
import com.walmart.walmartcompare.Constants.Companion.ITEM_RATING
import com.walmart.walmartcompare.Constants.Companion.ITEM_REVIEW_COUNT
import com.walmart.walmartcompare.Constants.Companion.ITEM_SHORT_DESC
import com.walmart.walmartcompare.R
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        val requestManager : RequestManager = Glide.with(this);

        val intent = getIntent()
        val imageURL = intent.getStringExtra(ITEM_IMAGE_URL)
        val name = intent.getStringExtra(ITEM_NAME)
        val price = intent.getDoubleExtra(ITEM_PRICE, 0.00)
        val rating = intent.getStringExtra(ITEM_RATING)
        val reviewCount = intent.getIntExtra(ITEM_REVIEW_COUNT, 0)
        val shortDescription = intent.getStringExtra(ITEM_SHORT_DESC)
        val longDescription = intent.getStringExtra(ITEM_LONG_DESC)

        val requestOptions : RequestOptions = RequestOptions().centerCrop();
        if (imageURL != null && imageURL.isNotEmpty()) {
            requestOptions
                .placeholder(R.drawable.placeholder)
                .fallback(R.drawable.placeholder)
                .error(R.drawable.outline_broken_image_24)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
            requestManager
                    .load(imageURL)
                    .apply(requestOptions)
                    .into(item_image_view)
        } else {
            requestManager
                    .load(R.drawable.outline_photo_24)
                    .apply(requestOptions)
                    .into(item_image_view)
        }
        item_name.text = name
        item_price.text = resources.getString(R.string.product_price, price)
        val reviewRating: Float = rating?.toFloat() ?: 0f
        item_rating_bar.rating = reviewRating
        item_rating_bar.contentDescription = resources.getString(R.string
                .rating_bar_content_description, reviewRating)
        item_review_count.text = resources.getString(R.string.review_count_detail_view_text, reviewCount)
        item_review_count.contentDescription = resources.getString(R.string
                .review_count_content_description, reviewCount)
        item_short_description.text = shortDescription
        item_long_description.text = longDescription
        add_to_list_button.setOnClickListener {
            Toast.makeText(this, "Added to list!", Toast.LENGTH_LONG).show()
        }
    }
}
