package com.example.tibiatatics.remote

import android.util.Log
import com.example.tibiatatics.model.NewsModel
import com.example.tibiatatics.model.NewsListResponse
import com.example.tibiatatics.model.toModel
import com.example.tibiatatics.ui.adapter.HomeFragmentAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsModelWebClient() {

    private val apiInterface: ApiNews = RetrofitSetup.createService(ApiNews::class.java)

    suspend fun loadFrom(): List<NewsModel>? {
        val resposta = apiInterface.getNewsLasted()
        return resposta.body()?.news?.map {
            it.toModel()
        }
    }
}
