package com.tatics.tibiatatics.model.response

import com.tatics.tibiatatics.model.models.BostedCreature

data class BostedCreatureResponse(
    val creatures: InfoCreatures
)

data class InfoCreatures(
    val boosted: Bosted,
    val creatureList: List<CreatureList>,
    val information: DetailInformation
)

data class CreatureList(
    val featured: Boolean,
    val image_url: String,
    val name: String,
    val race: String
)

data class Bosted(
    val featured: Boolean,
    val image_url: String,
    val name: String,
    val race: String
)

data class DetailInformation(
    val api: InfoApiCreature,
    val status: InfoStatus,
    val timeStamp: String
)

data class InfoApiCreature(
    val commit: String,
    val realease: String,
    val version: Int
)

data class InfoStatus(
    val error: Int,
    val http_code: Int,
    val message: String
)

fun Bosted.toModel(): BostedCreature {
    return BostedCreature(
        featured = featured,
        image_url = image_url,
        name = name,
        race = race
    )
}

