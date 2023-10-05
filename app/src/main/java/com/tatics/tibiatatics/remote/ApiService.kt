package com.tatics.tibiatatics.remote

import com.tatics.tibiatatics.model.response.BostedBossResponse
import com.tatics.tibiatatics.model.response.BostedCreatureResponse
import com.tatics.tibiatatics.model.response.GuildDetailResponse
import com.tatics.tibiatatics.model.response.GuildsResponse
import com.tatics.tibiatatics.model.response.NewsDetailResponse
import com.tatics.tibiatatics.model.response.NewsListResponse
import com.tatics.tibiatatics.model.response.RankResponse
import com.tatics.tibiatatics.model.response.SearchResponse
import com.tatics.tibiatatics.model.response.WorldDetailResponse
import com.tatics.tibiatatics.model.response.WorldResponse
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

    @GET("/v4/guilds/{world}")
    suspend fun getGuilds(@Path("world") world: String): Response<GuildsResponse>

    @GET("/v4/guild/{name}")
    suspend fun getGuildDetail(@Path("name") name: String): Response<GuildDetailResponse>

}
