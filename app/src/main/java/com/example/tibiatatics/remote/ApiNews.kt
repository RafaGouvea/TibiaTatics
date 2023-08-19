package com.example.tibiatatics.remote

import com.example.tibiatatics.model.NewsDetailResponse
import com.example.tibiatatics.model.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiNews {
    @GET("/v4/news/latest")
    suspend fun getNewsLasted(): Response<NewsListResponse>

    @GET("/v4/news/id/{id}")
    suspend fun getNewsDetail(@Path("id") id: List<Int>?): List<NewsDetailResponse>

}
