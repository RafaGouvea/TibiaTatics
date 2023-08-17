package com.example.tibiatatics.model

data class NewsDetailResponse(
    val date: String,
    val title: String,
    val url: String,
    val content: String
)

fun NewsDetailResponse.toModel(): NewsDetailModel {
    return NewsDetailModel(
        date = date,
        title = title,
        url = url,
       content = content
    )
}