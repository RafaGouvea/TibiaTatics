package com.tatics.tibiatatics.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tatics.tibiatatics.model.models.GuildsModel
import com.tatics.tibiatatics.model.models.WorldsModel
import com.tatics.tibiatatics.remote.WebClient

class GuildsViewModel: ViewModel() {

    val guildsModelLiveData = MutableLiveData<List<GuildsModel>?>()
    val worldsModelLiveData = MutableLiveData<WorldsModel?>()
    private var newsModelWebClient = WebClient()

    suspend fun loadGuilds(world: String) {
        val guilds = newsModelWebClient.getGuilds(world)
        guildsModelLiveData.postValue(guilds)
    }

    suspend fun loadWorlds(){
        val worlds = newsModelWebClient.loadWorlds()
        worldsModelLiveData.postValue(worlds)
    }
}
