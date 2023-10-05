package com.tatics.tibiatatics.model.models

data class GuildDetailModel(
    val guild: GuildMod,
    val information: InformationGuildMod
)

data class GuildMod(
    val name: String,
    val world: String,
    val logo_url: String,
    val description: String,
    val guildHalls: List<GuildHallsMod>,
    val active: Boolean,
    val founded: String,
    val open_applications: Boolean,
    val homepage: String,
    val in_war: Boolean,
    val players_online: Int,
    val players_offline: Int,
    val members_total: Int,
    val members_invited: Int,
    val members: List<MembersMod>,
    val invites: List<InvitesMod>
)

data class GuildHallsMod(
    val name: String,
    val world: String,
    val paid_until: String,
)

data class MembersMod(
    val name: String,
    val title: String,
    val rank: String,
    val vocation: String,
    val level: Int,
    val joined: String,
    val status: String,
)

data class InvitesMod(
    val name: String,
    val date: String
)

data class InformationGuildMod(
    val api: GuildApiMod,
    val timestamp: String,
    val status: StatusApiGuildMod
)

data class GuildApiMod(
    val version: Int,
    val release: String,
    val commit: String
)

data class StatusApiGuildMod(
    val http_code: Int
)