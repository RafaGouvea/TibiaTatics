package com.example.tibiatatics.model

data class WorldsStatus(
    val players_online: Int,
    val regular_worlds: List<RegularWorlds>
)