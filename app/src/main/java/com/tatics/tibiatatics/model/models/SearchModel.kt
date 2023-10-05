package com.tatics.tibiatatics.model.models

data class SearchModel(
    val character: CharacterInfoModels,
    val achievements: List<AchievementsModels>,
    val deaths: List<DeathsModel>,
    val other_characters: List<OtherCharactersModel>
)

data class OtherCharactersModel(
    val name: String? = "",
    val world: String? = "",
    val status: String? = "",
    val deleted: Boolean? = false,
    val main: Boolean? = false,
    val traded: Boolean? = false
)

data class DeathsModel(
    val time: String? = "",
    val level: Int? = 0,
    val killers: List<KillersModel>,
    val reason: String? = ""
)

data class KillersModel(
    val name: String? = ""
)

data class AchievementsModels(
    val name: String? = "",
    val grade: Int? = 0,
    val secret: Boolean? = false
)

data class CharacterInfoModels(
    val name: String? = "",
    val sex: String? = "",
    val title: String? = "",
    val unlocked_titles: Int? = 0,
    val vocation: String? = "",
    val level: Int? = 0,
    val achievement_points: Int? = 0,
    val world: String? = "",
    val residence: String? = "",
    val guild: GuildModel,
    val last_login: String? = "",
    val account_status: String? = "",
    val comment: String? = "",
)



data class GuildModel(
    val name: String? = "",
    val rank: String? = ""
)
