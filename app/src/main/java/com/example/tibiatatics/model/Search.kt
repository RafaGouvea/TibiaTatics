package com.example.tibiatatics.model

data class Search(
    val character: CharacterInfo,
    val achievements: List<Achievements>,
    val deaths: List<Deaths>
)

data class Deaths(
    val time: String,
    val level: Int,
    val killers: List<Killers>,
    val reason: String
)

data class Killers(
    val name: String
)

data class Achievements(
    val name: String,
    val gradle: Int,
    val secret: Boolean
)

data class CharacterInfo(
    val name: String,
    val sex: String,
    val title: String,
    val unlocked_titles: Int,
    val vocation: String,
    val level: Int,
    val achievement_points: Int,
    val world: String,
    val residence: String,
    val guild: Guild,
    val last_login: String,
    val account_status: String,
    val comment: String,
)



data class Guild(
    val name: String,
    val rank: String
)
