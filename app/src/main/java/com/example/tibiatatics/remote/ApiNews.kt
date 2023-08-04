package com.example.tibiatatics.remote

import com.example.tibiatatics.model.NewsListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiNews {

    @GET("/v4/news/latest")
    fun getNewsLasted(): Call<NewsListResponse>

}