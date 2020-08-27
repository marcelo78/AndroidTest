package com.mac.intacttest.backend

import com.mac.intacttest.activities.data.ProductsCollection
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceUtil {

    @GET("everything")
    suspend fun getProducts(
        @Query("q") q: String = "bitcoin",
        @Query("from") from: String = "2020-08-20",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): ProductsCollection

}