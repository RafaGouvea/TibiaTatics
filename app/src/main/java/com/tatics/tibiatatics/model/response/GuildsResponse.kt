package com.tatics.tibiatatics.model.response

import com.tatics.tibiatatics.model.models.GuildsModel

data class GuildsResponse(
    val guilds: GuildsDetailResponse,
    val information: InformationGuildsResponse
)

data class GuildsDetailResponse(
    val world: String,
    val active: List<Active>,
)

data class Active(
    val name: String,
    val logo_url: String,
    val description: String
)

data class InformationGuildsResponse(
    val api: ApiGuildResponse,
    val timestamp: String,
    val status: StatusApiResponse
)

data class ApiGuildResponse(
    val version: Int,
    val release: String,
    val commit: String
)

data class StatusApiResponse(
    val http_code: Int
)

fun Active.toModel(): GuildsModel{
    return GuildsModel(
        name = name,
        logo_url = logo_url,
        description = description
    )
}

