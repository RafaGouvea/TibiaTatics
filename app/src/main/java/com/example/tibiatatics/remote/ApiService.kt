package com.example.tibiatatics.remote

import com.example.tibiatatics.model.BostedBossResponse
import com.example.tibiatatics.model.BostedCreatureResponse
import com.example.tibiatatics.model.NewsDetailResponse
import com.example.tibiatatics.model.NewsListResponse
import com.example.tibiatatics.model.RankResponse
import com.example.tibiatatics.model.SearchResponse
import com.example.tibiatatics.model.WorldDetailResponse
import com.example.tibiatatics.model.WorldResponse
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

    @GET("/v4/worlds")
    suspend fun getWorlds(): Response<WorldResponse>

    @GET("/v4/character/{name}")
    suspend fun searchCharacter(@Path("name") name: String): Response<SearchResponse>

    @GET("/v4/highscores/{world}/{category}/{vocation}/{page}")
    suspend fun getRank(@Path("world") world: String, @Path("category") category: String, @Path("vocation") vocation: String, @Path("page") page: Int): Response<RankResponse>

    @GET("/v4/world/{name}")
    suspend fun getWorldDetail(@Path("name") name: String): Response<WorldDetailResponse>

}
