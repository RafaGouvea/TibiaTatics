package com.example.tibiatatics.model

data class SearchResponse(
    val character: PersonaCharacterResponse,
    val information: InformationApiCharacter
)

data class PersonaCharacterResponse(
    val character: CharacterResponse,
    val achievements: List<AchievementsResponse>,
    val deaths: List<DeathsResponse>
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
    val gradle: Int,
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

fun PersonaCharacterResponse.toModel(): Search {
    return Search(
        character = CharacterInfo(
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
            guild = Guild(
                name = character.guild.name,
                rank = character.guild.rank,
            )
        ),
        achievements = achievements.map { achiviements ->
            Achievements(
                name = achiviements.name,
                gradle = achiviements.gradle,
                secret = achiviements.secret
            )
        },
        deaths = deaths.map {
            Deaths(
                time = it.time,
                level = it.level,
                killers = it.killers.map { killers ->
                    Killers(
                        name = killers.name
                    )
                },
                reason = it.reason
            )
        }
    )
}

