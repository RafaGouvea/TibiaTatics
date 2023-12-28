package com.tatics.tibiatatics.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatics.tibiatatics.model.models.RankModel
import com.tatics.tibiatatics.model.models.WorldsModel
import com.tatics.tibiatatics.remote.WebClient

class HighscoreViewModel: ViewModel() {

    val rankLiveData = MutableLiveData<RankModel?>()
    private var newsModelWebClient = WebClient()
    val worldsModelLiveData = MutableLiveData<WorldsModel?>()


    suspend fun getRank(world: String, category: String, vocation: String, page: Int){
        val rank = newsModelWebClient.getRank(world, category, vocation, page)
        rankLiveData.postValue(rank)
    }

    suspend fun loadWorlds(){
        val worlds = newsModelWebClient.loadWorlds()
        worldsModelLiveData.postValue(worlds)
    }
}
