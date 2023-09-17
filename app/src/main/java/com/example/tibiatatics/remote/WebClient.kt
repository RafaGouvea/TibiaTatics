package com.example.tibiatatics.remote

import android.util.Log
import com.example.tibiatatics.model.BostedBoss
import com.example.tibiatatics.model.BostedCreature
import com.example.tibiatatics.model.NewsDetailModel
import com.example.tibiatatics.model.NewsModel
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
        }catch (e: Exception){
            Log.e("###", "loadBostedCreature: ", e)
        }
        return null
    }

    suspend fun loadBostedBoss(): BostedBoss? {
        try {
            val resposta = apiInterface.getBostedBoss()
            if (resposta.isSuccessful) {
                Log.i("###", "loadBostedBoss: ${resposta.body()?.boostable_bosses?.boosted?.toModel()}")
                return resposta.body()?.boostable_bosses?.boosted?.toModel()
            } else {
                Log.e("###", "loadBostedBoss: Response not sucessful")
            }
        }catch (e: Exception){
            Log.e("###", "loadBostedCreature: ", e)
        }
        return null
    }
}