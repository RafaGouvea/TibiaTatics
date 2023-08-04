package com.example.tibiatatics.remote

import android.util.Log
import com.example.tibiatatics.model.NewsModel
import com.example.tibiatatics.model.NewsListResponse
import com.example.tibiatatics.model.toModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsModelWebClient() {

    var listNewsModel: List<NewsModel> = emptyList()

    fun loadMovieListFrom() {

        val apiInterface: ApiNews = RetrofitSetup.createService(ApiNews::class.java)
        val call: Call<NewsListResponse> = apiInterface.getNewsLasted()

        call.enqueue(object : Callback<NewsListResponse> {
            override fun onResponse(
                call: Call<NewsListResponse>,
                response: Response<NewsListResponse>,
            ) {
                if (response.isSuccessful) {
                    val newsListResponse: NewsListResponse? = response.body()
                    val newsModelList = newsListResponse?.news?.map {
                        it.toModel()
                    }
                    if (newsModelList != null) {
                        listNewsModel = newsModelList
                    }
                }
            }
            override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
                Log.e("###", t.message ?: "")
            }
        })
    }

}
