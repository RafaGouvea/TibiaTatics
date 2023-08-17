package com.example.tibiatatics.remote

import android.util.Log
import com.example.tibiatatics.model.NewsDetailModel
import com.example.tibiatatics.model.NewsModel
import com.example.tibiatatics.model.toModel

class NewsModelWebClient() {

    private val apiInterface: ApiNews = RetrofitSetup.createService(ApiNews::class.java)


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

    

}
