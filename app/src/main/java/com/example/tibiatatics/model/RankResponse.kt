package com.example.tibiatatics.model

data class RankResponse(
    val highscores: HighscoresResponse,
    val information: InformationRankResponse
)

data class HighscoresResponse(
    val world: String,
    val category: String,
    val vocation: String,
    val highscore_age: Int,
    val highscore_list: List<HighscoreListResponse>,
    val highscore_page: HighscorePageResponse
)

data class HighscoreListResponse(
    val rank: Int,
    val name: String,
    val vocation: String,
    val world: String,
    val level: Int,
    val value: Long
)

data class HighscorePageResponse(
    val current_page: Int,
    val total_pages: Int,
    val total_records: Int
)

data class InformationRankResponse(
    val api: ApiRankResponse,
    val timestamp: String,
    val status: StatusRankResponse
)

data class ApiRankResponse(
    val version: Int,
    val release: String,
    val commit: String
)

data class StatusRankResponse(
    val http_code: Int
)

fun RankResponse.toModel(): RankModel {
    return RankModel(
        highscores = HighscoresModel(
            world = highscores.world,
            category = highscores.category,
            vocation = highscores.vocation,
            highscore_age = highscores.highscore_age,
            highscore_list = highscores.highscore_list.map { highscoreResponse ->
                HighscoreListModel(
                    rank = highscoreResponse.rank,
                    name = highscoreResponse.name,
                    vocation = highscoreResponse.vocation,
                    world = highscoreResponse.world,
                    level = highscoreResponse.level,
                    value = highscoreResponse.value
                )
            },
            highscore_page = highscores.highscore_page.let { highscorePage ->
                HighscorePageModel(
                    current_page = highscorePage.current_page,
                    total_pages = highscorePage.total_pages,
                    total_records = highscorePage.total_records
                )
            }
        ),
        information = InformationRankModel(
            api = ApiRankModel(
                version = information.api.version,
                commit = information.api.commit,
                release = information.api.release
            ),
            timestamp = information.timestamp,
            status = StatusRankModel(
                http_code = information.status.http_code
            )
        )
    )
}