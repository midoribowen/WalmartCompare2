package com.walmart.walmartcompare

import com.google.gson.annotations.SerializedName

/**
 * Data class to hold search response from search API calls
 */

data class WalmartSearchResponse(
    @SerializedName("query") val query: String,
    @SerializedName("sort") val sort: String,
    @SerializedName("responseGroup") val responseGroup: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("numItems") val numItems: Int,
    @SerializedName("items") val items: List<SearchItem>
//    @SerializedName("facets") val facets: List<Any>
)