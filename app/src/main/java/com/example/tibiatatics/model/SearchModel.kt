package com.example.tibiatatics.model

data class SearchModel(
    val character: CharacterInfoModels,
    val achievements: List<AchievementsModels>,
    val deaths: List<DeathsModel>,
    val other_characters: List<OtherCharactersModel>
)

data class OtherCharactersModel(
    val name: String,
    val world: String,
    val status: String,
    val deleted: Boolean,
    val main: Boolean,
    val traded: Boolean
)

data class DeathsModel(
    val time: String,
    val level: Int,
    val killers: List<KillersModel>,
    val reason: String
)

data class KillersModel(
    val name: String
)

data class AchievementsModels(
    val name: String,
    val grade: Int,
    val secret: Boolean
)

data class CharacterInfoModels(
    val name: String,
    val sex: String,
    val title: String,
    val unlocked_titles: Int,
    val vocation: String,
    val level: Int,
    val achievement_points: Int,
    val world: String,
    val residence: String,
    val guild: GuildModel,
    val last_login: String,
    val account_status: String,
    val comment: String,
)



data class GuildModel(
    val name: String,
    val rank: String
)
