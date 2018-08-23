package com.walmart.walmartcompare.api

import android.util.Log
import com.walmart.walmartcompare.BuildConfig
import com.walmart.walmartcompare.model.SearchItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val TAG = WalmartService::class.java.simpleName

fun searchItems(
        service: WalmartService,
        query: String,
        pageStart: Int,
        itemsPerPage: Int,
        onSuccess: (items: List<SearchItem>) -> Unit,
        onError: (error: String) -> Unit) {
    Log.d(TAG, "query: $query, pageStart: $pageStart, itemsPerPage: $itemsPerPage")

    service.search(query, pageStart, itemsPerPage, BuildConfig.WALMART_API_KEY).enqueue(
            object : Callback<WalmartSearchResponse> {
                override fun onFailure(call: Call<WalmartSearchResponse>?, t: Throwable) {
                    Log.e(TAG, "fail to get data", t)
                    onError(t.message ?: "unknown error")
                }

                override fun onResponse(call: Call<WalmartSearchResponse>?, response: Response<WalmartSearchResponse>) {
                    Log.d(TAG, "success! \n response=\n ${response.body()}")
                    if (response.isSuccessful) {
                        val items = response.body()?.items ?: emptyList()
                        onSuccess(items)
                    } else {
                        onError(response.errorBody()?.string() ?: "unknown error")
                    }
                }
            }
    )
}

/**
 * Walmart API communication setup via Retrofit
 */

interface WalmartService {
    @GET("search?format=json")
    fun search(@Query("query") query: String,
               @Query("start") page: Int,
               @Query("numItems") per_page: Int,
               @Query("apiKey") apiKey: String): Call<WalmartSearchResponse>

    companion object {
        private const val BASE_URL = "https://api.walmartlabs.com/v1/"

        fun create(): WalmartService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WalmartService::class.java)
        }
    }
}