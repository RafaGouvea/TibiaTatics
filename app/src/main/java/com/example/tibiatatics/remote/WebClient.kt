package com.example.tibiatatics.remote

import android.util.Log
import com.example.tibiatatics.model.BostedBoss
import com.example.tibiatatics.model.BostedCreature
import com.example.tibiatatics.model.NewsDetailModel
import com.example.tibiatatics.model.NewsModel
import com.example.tibiatatics.model.RankModel
import com.example.tibiatatics.model.SearchModel
import com.example.tibiatatics.model.WorldDetailModel
import com.example.tibiatatics.model.WorldsModel
import com.example.tibiatatics.model.toModel

class WebClient() {

    private val apiInterface: ApiService = RetrofitSetup.createService(ApiService::class.java)


    suspend fun loadNewsFrom(): List<NewsModel>? {
        return try {
            val resposta = apiInterface.getNewsLasted()
            resposta.body()?.news?.map {
                it.toModel()
            }
        } catch (e: Exception) {
            Log.e("###", "loadNewsFrom: ", e)
            null
        }
    }

    suspend fun loadNewsDetailFrom(id: String): NewsDetailModel? {
        try {
            val resposta = apiInterface.getNewsDetail(id)
            if (resposta.isSuccessful) {
                return resposta.body()?.news?.toModel()
            } else {
                Log.e("###", "loadNewsDetailFrom: Response not successful")
            }
        } catch (e: Exception) {
            Log.e("###", "loadNewsDetailFrom: ", e)
        }
        return null
    }

    suspend fun loadBostedCreature(): BostedCreature? {
        try {
            val resposta = apiInterface.getBostedCreature()
            if (resposta.isSuccessful) {
                return resposta.body()?.creatures?.boosted?.toModel()
            } else {
                Log.e("###", "loadBostedCreature: Response not sucessful")
            }
        } catch (e: Exception) {
            Log.e("###", "loadBostedCreature: ", e)
        }
        return null
    }

    suspend fun loadBostedBoss(): BostedBoss? {
        try {
            val resposta = apiInterface.getBostedBoss()
            if (resposta.isSuccessful) {
                return resposta.body()?.boostable_bosses?.boosted?.toModel()
            } else {
                Log.e("###", "loadBostedBoss: Response not sucessful")
            }
        } catch (e: Exception) {
            Log.e("###", "loadBostedCreature: ", e)
        }
        return null
    }

    suspend fun loadWorlds(): WorldsModel? {
        try {
            val resposta = apiInterface.getWorlds()
            if (resposta.isSuccessful) {
                return resposta.body()?.worlds?.toModel()
            } else {
                Log.e("###", "loadPlayersOnline: Response not sucessful")
            }
        } catch (e: Exception) {
            Log.e("###", "loadBostedCreature: ", e)
        }
        return null
    }

    suspend fun searchCharacter(name: String): SearchModel? {
        try {
            val resposta = apiInterface.searchCharacter(name)
            if (resposta.isSuccessful) {
                return resposta.body()?.character?.toModel()
            } else {
                Log.e("###", "searchCharacter: Response not sucessful ")
            }

        } catch (e: java.lang.Exception) {
            Log.e("###", "searchCharacter: ", e)
        }
        return null
    }

    suspend fun getRank(world: String, category: String, vocation: String, page: Int): RankModel? {
        try {
            val resposta = apiInterface.getRank(world, category, vocation, page)
            if (resposta.isSuccessful) {
                Log.i("@@@", "getRank: ${resposta.body()?.toModel()}")
                return resposta.body()?.toModel()
            } else {
                Log.e("###", "getRank: Response not sucessful ")
            }

        } catch (e: java.lang.Exception) {
            Log.e("###", "getRank: ", e)
        }
        return null
    }

    suspend fun getWorldDetail(name: String): List<WorldDetailModel>? {
        try {
            val resposta = apiInterface.getWorldDetail(name)
            if (resposta.isSuccessful) {
                Log.i("###", "getWorldDetail: ${resposta.body()?.world?.online_players?.map { it.toModel() }}")
                return resposta.body()?.world?.online_players?.map { it.toModel() }
            } else {
                Log.e("###", "getRank: Response not sucessful ")
            }

        } catch (e: java.lang.Exception) {
            Log.e("###", "getWorldDetail: ", e)
        }
        return null
    }
}
