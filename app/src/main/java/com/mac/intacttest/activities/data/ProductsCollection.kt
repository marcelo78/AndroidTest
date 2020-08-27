package com.mac.intacttest.activities.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsCollection(
    @SerialName(value = "status") val status: String,
    @SerialName(value = "totalResults") val totalResults: Int,
    @SerialName(value = "articles") val articles: ArrayList<Article>
) {
    @Serializable
    data class Article(
        @SerialName(value = "source") val source: Source,
        @SerialName(value = "author") val author: String,
        @SerialName(value = "title") val title: String,
        @SerialName(value = "description") val description: String,
        @SerialName(value = "urlToImage") val urlToImage: String
    ) {
        @Serializable
        data class Source(
            @SerialName(value = "name") val name: String
        )
    }

}
