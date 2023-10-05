package com.tatics.tibiatatics.model.response

import com.tatics.tibiatatics.model.models.AchievementsModels
import com.tatics.tibiatatics.model.models.CharacterInfoModels
import com.tatics.tibiatatics.model.models.DeathsModel
import com.tatics.tibiatatics.model.models.GuildModel
import com.tatics.tibiatatics.model.models.KillersModel
import com.tatics.tibiatatics.model.models.OtherCharactersModel
import com.tatics.tibiatatics.model.models.SearchModel

data class SearchResponse(
    val character: PersonaCharacterResponse,
    val information: InformationApiCharacter
)

data class PersonaCharacterResponse(
    val character: CharacterResponse,
    val achievements: List<AchievementsResponse>? = null,
    val deaths: List<DeathsResponse>? = null,
    val other_characters: List<OtherCharactersResponse>? = null
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
    val characterInfo = CharacterInfoModels(
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
    )

    val achievementsList = achievements?.map { achievements ->
        AchievementsModels(
            name = achievements.name,
            grade = achievements.grade,
            secret = achievements.secret
        )
    } ?: emptyList()

    val deathsList = deaths?.map { death ->
        DeathsModel(
            time = death.time,
            level = death.level,
            killers = death.killers.map { killers ->
                KillersModel(
                    name = killers.name
                )
            },
            reason = death.reason
        )
    } ?: emptyList()

    val otherCharacters = other_characters?.map { otherCharacters ->
        OtherCharactersModel(
            name = otherCharacters.name,
            world = otherCharacters.world,
            status = otherCharacters.status,
            deleted = otherCharacters.deleted,
            main = otherCharacters.main,
            traded = otherCharacters.traded
        )
    } ?: emptyList()

    return SearchModel(
        character = characterInfo,
        achievements = achievementsList,
        deaths = deathsList,
        other_characters = otherCharacters
    )
}

