package com.tatics.tibiatatics.model.models

data class WorldsModel(
    val players_online: Int,
    val regular_worlds: List<RegularWorldsModel>
)

data class RegularWorldsModel(
    val name: String,
    val status: String,
    val players_online: Int,
    val location: String,
    val pvp_type: String,
    val premium_only: Boolean,
    val transfer_type: String,
    val battleye_protected: Boolean,
    val battleye_date: String,
    val game_world_type: String,
    val tournament_world_type: String,
)