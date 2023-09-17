package com.example.tibiatatics.model

data class NewsDetailResponse(
    val news: InfoNews,
    val information: Information

)

data class Information(
    val api: InfoApi,
    val timesStamp: String,
    val status: Status,
)

data class InfoApi(
    val version: Int,
    val release: String,
    val commit: String,
)

data class Status (
    val http_code: Int
)

data class InfoNews(
    val date: String,
    val title: String,
    val url: String,
    val content: String
)

fun InfoNews.toModel(): NewsDetailModel {
    return NewsDetailModel(
        date = date,
        title = title,
        url = url,
       content = content
    )
}