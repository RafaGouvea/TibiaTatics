package com.example.tibiatatics.model

data class BostedBossResponse(
    val boostable_bosses: InfoBoss,
    val information: DetailBossInformation
)

data class InfoBoss(
    val boostable_boss_list: List<BossList>,
    val boosted: BossBosted
)

data class BossList(
    val featured: Boolean,
    val image_url: String,
    val name: String,
)

data class BossBosted(
    val featured: Boolean,
    val image_url: String,
    val name: String,
)

data class DetailBossInformation(
    val api: InfoApiBoss,
    val status: InfoStatusBoss,
    val timeStamp: String
)

data class InfoApiBoss(
    val commit: String,
    val release: String,
    val version: Int
)

data class InfoStatusBoss(
    val http_code: Int,
)

fun BossBosted.toModel(): BostedBoss{
    return BostedBoss(
        featured_boss = featured,
        image_url_boss = image_url,
        name_boss = name
    )
}