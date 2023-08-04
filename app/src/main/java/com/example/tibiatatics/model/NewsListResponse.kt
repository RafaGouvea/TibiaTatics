package com.example.tibiatatics.model

data class NewsListResponse(
    val news: List<NewsResponse>,
)

data class NewsResponse(
    val id: Int,
    val date: String,
    val news: String,
    val category: String,
    val type: String,
    val url: String,
    val urlApi: String
)

fun NewsResponse.toModel(): NewsModel {
    return NewsModel(
        id = id,
        date = date,
        news = news,
        category = category,
        type = type,
        url = url,
        urlApi = urlApi
    )
}