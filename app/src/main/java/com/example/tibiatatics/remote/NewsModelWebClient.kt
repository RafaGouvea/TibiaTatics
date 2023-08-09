package com.example.tibiatatics.remote

import android.util.Log
import com.example.tibiatatics.model.NewsModel
import com.example.tibiatatics.model.toModel

class NewsModelWebClient() {

    private val apiInterface: ApiNews = RetrofitSetup.createService(ApiNews::class.java)

    suspend fun loadFrom(): List<NewsModel>? {
       return try {
            val resposta = apiInterface.getNewsLasted()
            resposta.body()?.news?.map {
                it.toModel()
            }
        } catch (e: Exception) {
           Log.e("###", "loadFrom: ", e)
            null
        }
    }
}
