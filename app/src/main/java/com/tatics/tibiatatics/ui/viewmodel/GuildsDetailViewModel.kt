package com.tatics.tibiatatics.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatics.tibiatatics.model.models.GuildDetailModel
import com.tatics.tibiatatics.remote.WebClient

class GuildsDetailViewModel: ViewModel() {

    val guildsDetailLiveData = MutableLiveData<GuildDetailModel?>()
    private var newsModelWebClient = WebClient()

    suspend fun loadGuildsDetail(name: String) {
        val members = newsModelWebClient.getGuildsDetail(name)
        guildsDetailLiveData.postValue(members)
    }

}