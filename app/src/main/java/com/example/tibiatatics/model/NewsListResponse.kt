package com.example.tibiatatics.model

data class NewsListResponse(
    val news: List<NewsResponse>,
)

data class NewsResponse(
    val id: Int,
    val date: String,
    val news: String,
    val url: String,
)

fun NewsResponse.toModel(): NewsModel {
    return NewsModel(
        id = id,
        date = date,
        title = news,
        url = url,
    )
}