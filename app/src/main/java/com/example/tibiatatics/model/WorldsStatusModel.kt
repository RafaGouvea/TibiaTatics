package com.example.tibiatatics.model

data class WorldsStatusModel(
    val players_online: Int,
    val regular_worlds: List<RegularWorlds>
)