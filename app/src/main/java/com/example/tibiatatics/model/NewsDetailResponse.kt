package com.example.tibiatatics.model

data class NewsDetailResponse(
    val content: String
)

fun NewsDetailResponse.toModel(): NewsDetailModel {
    return NewsDetailModel(
       content = content
    )
}