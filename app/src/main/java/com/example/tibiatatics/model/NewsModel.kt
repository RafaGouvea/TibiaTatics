package com.example.tibiatatics.model

data class NewsModel(
    val id: Int,
    val date: String? = null,
    val news: String? = null,
    val category: String? = null,
    val type: String? = null,
    val url: String? = null,
    val urlApi: String? = null
)