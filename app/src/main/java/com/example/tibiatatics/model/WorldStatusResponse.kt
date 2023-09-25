package com.example.tibiatatics.model

data class WorldResponse(
    val worlds: Worlds,
    val information: InformationWorld,
)

data class Worlds(
    val players_online: Int,
    val record_players: Int,
    val record_date: String,
    val regular_worlds: List<RegularWorlds>,
    val tournament_worlds: String
)

data class RegularWorlds(
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

data class InformationWorld(
    val apiWorld: ApiWorld,
    val timestamp: String,
    val status: StatusWorld,
)

data class StatusWorld(
    val http_code: Int
)

data class ApiWorld(
    val version: Int,
    val release: String,
    val commit: String
)

fun Worlds.toModel(): WorldsStatusModel{
    return WorldsStatusModel(
        players_online = players_online,
        regular_worlds = regular_worlds
    )
}

