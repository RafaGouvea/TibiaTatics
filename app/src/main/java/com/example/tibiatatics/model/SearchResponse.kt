package com.example.tibiatatics.model

data class SearchResponse(
    val character: PersonaCharacterResponse,
    val information: InformationApiCharacter
)

data class PersonaCharacterResponse(
    val character: CharacterResponse,
    val achievements: List<AchievementsResponse>,
    val deaths: List<DeathsResponse>,
    val other_characters: List<OtherCharactersResponse>
)

data class OtherCharactersResponse(
    val name: String,
    val world: String,
    val status: String,
    val deleted: Boolean,
    val main: Boolean,
    val traded: Boolean
)

data class DeathsResponse(
    val time: String,
    val level: Int,
    val killers: List<KillersResponse>,
    val reason: String
)

data class KillersResponse(
    val name: String,
)

data class CharacterResponse(
    val name: String,
    val sex: String,
    val title: String,
    val unlocked_titles: Int,
    val vocation: String,
    val level: Int,
    val achievement_points: Int,
    val world: String,
    val residence: String,
    val guild: GuildResponse,
    val last_login: String,
    val account_status: String,
    val comment: String,
)

data class AchievementsResponse(
    val name: String,
    val grade: Int,
    val secret: Boolean
)

data class GuildResponse(
    val name: String,
    val rank: String
)

data class InformationApiCharacter(
    val api: ApiSearch,
    val timestamp: String,
    val status: StatusSearch
)

data class ApiSearch(
    val version: Int,
    val release: String,
    val commit: String,
)

data class StatusSearch(
    val http_code: Int
)

fun PersonaCharacterResponse.toModel(): SearchModel {
    return SearchModel(
        character = CharacterInfoModels(
            name = character.name,
            sex = character.sex,
            title = character.title,
            unlocked_titles = character.unlocked_titles,
            vocation = character.vocation,
            level = character.level,
            achievement_points = character.achievement_points,
            world = character.world,
            residence = character.residence,
            last_login = character.last_login,
            account_status = character.account_status,
            comment = character.comment,
            guild = GuildModel(
                name = character.guild.name,
                rank = character.guild.rank,
            )
        ),
        achievements = achievements.map { achiviements ->
            AchievementsModels(
                name = achiviements.name,
                grade = achiviements.grade,
                secret = achiviements.secret
            )
        },
        deaths = deaths.map {
            DeathsModel(
                time = it.time,
                level = it.level,
                killers = it.killers.map { killers ->
                    KillersModel(
                        name = killers.name
                    )
                },
                reason = it.reason
            )
        },
        other_characters.map {
            OtherCharactersModel(
                name = it.name,
                world = it.world,
                status = it.status,
                deleted = it.deleted,
                main = it.main,
                traded = it.traded
            )
        }
    )
}

