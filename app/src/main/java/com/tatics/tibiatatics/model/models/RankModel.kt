package com.tatics.tibiatatics.model.models

data class RankModel(
    val highscores: HighscoresModel,
    val information: InformationRankModel
)

data class HighscoresModel(
    val world: String,
    val vocation: String,
    val highscore_age: Int,
    val highscore_list: List<HighscoreListModel>,
    val highscore_page: HighscorePageModel
)

data class HighscoreListModel(
    val rank: Int,
    val name: String,
    val vocation: String,
    val world: String,
    val level: Int,
    val category: String,
    val value: Long
)

data class HighscorePageModel(
    val current_page: Int,
    val total_pages: Int,
    val total_records: Int
)

data class InformationRankModel(
    val api: ApiRankModel,
    val timestamp: String,
    val status: StatusRankModel
)

data class ApiRankModel(
    val version: Int,
    val release: String,
    val commit: String
)

data class StatusRankModel(
    val http_code: Int
)