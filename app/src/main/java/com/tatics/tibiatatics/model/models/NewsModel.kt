package com.tatics.tibiatatics.model.models

data class NewsModel(
    val id: Int,
    val date: String? = null,
    val title: String? = null,
    val url: String? = null,
)