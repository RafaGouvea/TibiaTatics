package com.example.tibiatatics.remote

import com.example.tibiatatics.model.BostedBossResponse
import com.example.tibiatatics.model.BostedCreatureResponse
import com.example.tibiatatics.model.NewsDetailResponse
import com.example.tibiatatics.model.NewsListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/v4/news/latest")
    suspend fun getNewsLasted(): Response<NewsListResponse>

    @GET("/v4/news/id/{id}")
    suspend fun getNewsDetail(@Path("id") id: String): Response<NewsDetailResponse>

    @GET("/v4/creatures")
    suspend fun getBostedCreature(): Response<BostedCreatureResponse>

    @GET("/v4/boostablebosses")
    suspend fun getBostedBoss(): Response<BostedBossResponse>

}
