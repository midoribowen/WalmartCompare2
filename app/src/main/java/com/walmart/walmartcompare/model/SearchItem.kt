package com.walmart.walmartcompare.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "searchItems")
data class SearchItem(
        @PrimaryKey @SerializedName("itemId") val itemId: Int,
        @SerializedName("parentItemId") val parentItemId: Int,
        @SerializedName("name") val name: String?,
        @SerializedName("msrp") val msrp: Double,
        @SerializedName("salePrice") val salePrice: Double,
        @SerializedName("upc") val upc: String?,
        @SerializedName("categoryPath") val categoryPath: String?,
        @SerializedName("categoryNode") val categoryNode: String?,
        @SerializedName("shortDescription") val shortDescription: String?,
        @SerializedName("longDescription") val longDescription: String?,
        @SerializedName("brandName") val brandName: String?,
        @SerializedName("thumbnailImage") val thumbnailImage: String?,
        @SerializedName("mediumImage") val mediumImage: String?,
        @SerializedName("largeImage") val largeImage: String?,
        @SerializedName("productTrackingUrl") val productTrackingUrl: String?,
        @SerializedName("ninetySevenCentShipping") val ninetySevenCentShipping: Boolean,
        @SerializedName("standardShipRate") val standardShipRate: Double,
        @SerializedName("twoThreeDayShippingRate") val twoThreeDayShippingRate: Int,
    // size
        @SerializedName("color") val color: String?,
        @SerializedName("marketplace") val marketplace: Boolean,
        @SerializedName("sellerInfo") val sellerInfo: String?,
        @SerializedName("shipToStore") val shipToStore: Boolean,
        @SerializedName("freeShipToStore") val freeShipToStore: Boolean,
        @SerializedName("modelNumber") val modelNumber: String?,
        @SerializedName("productUrl") val productUrl: String?,
        @SerializedName("availableOnline") val availableOnline: Boolean,
        @SerializedName("stock") val stock: String?,
        @SerializedName("customerRating") val customerRating: String?,
        @SerializedName("customerRatingImage") val customerRatingImage: String?,
        @SerializedName("numReviews") val numReviews: Int,
        @SerializedName("clearance") val clearance: Boolean,
        @SerializedName("preOrder") val preOrder: Boolean,
        @SerializedName("preOrderShipsOn") val preOrderShipsOn: String?,
        @SerializedName("offerType") val offerType: String?,
        @SerializedName("rhid") val rhid: String?,
        @SerializedName("bundle") val bundle: Boolean,
    //attributes
        @SerializedName("addToCartUrl") val addToCartUrl: String?,
        @SerializedName("affiliateAddToCartUrl") val affiliateAddToCartUrl: String?,
        @SerializedName("freeShippingOver35Dollars") val freeShippingOver35Dollars: Boolean,
        @SerializedName("gender") val gender: String?,
        @SerializedName("age") val age: String?,
//    @SerializedName("imageEntities") val imageEntities: List<ImageEntity> = emptyList(),
        @SerializedName("isTwoDayShippingEligible") val isTwoDayShippingEligible: Boolean
//    @SerializedName("giftOptions") val giftOptions: GiftOptions
    //bestMarketplacePrice
)



data class ImageEntity(
    @SerializedName("thumbnailImage") val thumbnailImage: String,
    @SerializedName("mediumImage") val mediumImage: String,
    @SerializedName("largeImage") val largeImage: String,
    @SerializedName("entityType") val entityType: String
)

data class GiftOptions(
    @SerializedName("allowGiftWrap") val allowGiftWrap: Boolean,
    @SerializedName("allowGiftMessage") val allowGiftMessage: Boolean,
    @SerializedName("allowGiftReceipt") val allowGiftReceipt: Boolean
)
