package com.example.tibiatatics.remote

import com.example.tibiatatics.model.NewsListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiNews {
    @GET("/v4/news/latest")
    suspend fun getNewsLasted(): Response<NewsListResponse>
}
