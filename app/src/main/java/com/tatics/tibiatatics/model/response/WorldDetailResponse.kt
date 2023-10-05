package com.tatics.tibiatatics.model.response

import com.tatics.tibiatatics.model.models.WorldDetailModel

data class WorldDetailResponse(
    val world: WorldInfoResponse,
    val information: InformationWorldInfo
)

data class WorldInfoResponse(
    val name: String,
    val status: String,
    val players_online: Int,
    val record_players: Int,
    val record_date: String,
    val creation_date: String,
    val location: String,
    val pvp_type: String,
    val premium_only: Boolean,
    val transfer_type: String,
    val world_quest_titles: List<String>,
    val battleye_protected: Boolean,
    val battleye_date: String,
    val game_world_type: String,
    val tournament_world_type: String,
    val online_players: List<OnlinePlayers>
)

data class OnlinePlayers(
    val name: String,
    val level: Int,
    val vocation: String
)

data class InformationWorldInfo(
    val apiWorld: ApiWorldDetail,
    val timestamp: String,
    val status: StatusWorldDetail,
)

data class StatusWorldDetail(
    val http_code: Int
)

data class ApiWorldDetail(
    val version: Int,
    val release: String,
    val commit: String
)

fun OnlinePlayers.toModel(): WorldDetailModel {
    return WorldDetailModel(
        name = name,
        level = level,
        vocation = vocation
    )
}