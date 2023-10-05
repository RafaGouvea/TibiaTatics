package com.tatics.tibiatatics.model.response

import com.tatics.tibiatatics.model.models.GuildApiMod
import com.tatics.tibiatatics.model.models.GuildDetailModel
import com.tatics.tibiatatics.model.models.GuildHallsMod
import com.tatics.tibiatatics.model.models.GuildMod
import com.tatics.tibiatatics.model.models.InformationGuildMod
import com.tatics.tibiatatics.model.models.InvitesMod
import com.tatics.tibiatatics.model.models.MembersMod
import com.tatics.tibiatatics.model.models.StatusApiGuildMod

data class GuildDetailResponse(
    val guild: GuildResp,
    val information: InfoGuildResponse
)

data class GuildResp(
    val name: String,
    val world: String,
    val logo_url: String,
    val description: String,
    val guildHalls: List<GuildHallsResponse>? = null,
    val active: Boolean,
    val founded: String,
    val open_applications: Boolean,
    val homepage: String,
    val in_war: Boolean,
    val players_online: Int,
    val players_offline: Int,
    val members_total: Int,
    val members_invited: Int,
    val members: List<MembersResponse>? = null,
    val invites: List<InvitesResponse>? = null
)

data class GuildHallsResponse(
    val name: String,
    val world: String,
    val paid_until: String,
)

data class MembersResponse(
    val name: String,
    val title: String,
    val rank: String,
    val vocation: String,
    val level: Int,
    val joined: String,
    val status: String,
)

data class InvitesResponse(
    val name: String,
    val date: String
)

data class InfoGuildResponse(
    val api: GuildApiResp,
    val timestamp: String,
    val status: StatusApiGuildResp
)

data class GuildApiResp(
    val version: Int,
    val release: String,
    val commit: String
)

data class StatusApiGuildResp(
    val http_code: Int
)

fun GuildDetailResponse.toModel(): GuildDetailModel {
    return GuildDetailModel(
        guild = GuildMod(
            name = guild.name,
            world = guild.world,
            logo_url = guild.logo_url,
            description = guild.description,
            guildHalls = guild.guildHalls.orEmpty().map {
                GuildHallsMod(
                    name = it.name,
                    world = it.world,
                    paid_until = it.paid_until
                )
            },
            active = guild.active,
            founded = guild.founded,
            open_applications = guild.open_applications,
            homepage = guild.homepage,
            in_war = guild.in_war,
            players_online = guild.players_online,
            players_offline = guild.players_offline,
            members_total = guild.members_total,
            members_invited = guild.members_invited,
            members = guild.members.orEmpty().map {
                MembersMod(
                    name = it.name,
                    title = it.title,
                    rank = it.rank,
                    vocation = it.vocation,
                    level = it.level,
                    joined = it.joined,
                    status = it.status
                )
            },
            invites = guild.invites.orEmpty().map {
                InvitesMod(
                    name = it.name,
                    date = it.date
                )
            }
        ),
        information = InformationGuildMod(
            api = GuildApiMod(
                version = information.api.version,
                release = information.api.release,
                commit = information.api.commit
            ),
            timestamp = information.timestamp,
            status = StatusApiGuildMod(
                http_code = information.status.http_code
            )
        )
    )
}