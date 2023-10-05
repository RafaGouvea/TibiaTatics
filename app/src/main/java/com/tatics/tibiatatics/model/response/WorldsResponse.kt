package com.tatics.tibiatatics.model.response

import com.tatics.tibiatatics.model.models.RegularWorldsModel
import com.tatics.tibiatatics.model.models.WorldsModel

data class WorldResponse(
    val worlds: WorldsResponse,
    val information: InformationWorld,
)

data class WorldsResponse(
    val players_online: Int,
    val record_players: Int,
    val record_date: String,
    val regular_worlds: List<RegularWorldsResponse>,
    val tournament_worlds: String
)

data class RegularWorldsResponse(
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

fun WorldsResponse.toModel(): WorldsModel {
    return WorldsModel(
        players_online = players_online,
        regular_worlds = regular_worlds.map {
            RegularWorldsModel(
                name = it.name,
                status = it.status,
                players_online = it.players_online,
                location = it.location,
                pvp_type = it.pvp_type,
                premium_only = it.premium_only,
                transfer_type = it.transfer_type,
                battleye_protected = it.battleye_protected,
                battleye_date = it.battleye_date,
                game_world_type = it.game_world_type,
                tournament_world_type = it.tournament_world_type
            )
        }
    )
}

