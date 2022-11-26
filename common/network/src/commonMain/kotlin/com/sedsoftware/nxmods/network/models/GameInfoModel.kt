package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class GameInfoModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("forum_url") val forumUrl: String,
    @SerialName("nexusmods_url") val nexusmodsUrl: String,
    @SerialName("genre") val genre: String,
    @SerialName("file_count") val filesCount: Long,
    @SerialName("downloads") val downloadsCount: Long,
    @SerialName("domain_name") val domain: String,
    @SerialName("file_views") val filesViews: Long,
    @SerialName("authors") val authors: Long,
    @SerialName("file_endorsements") val fileEndorsements: Long,
    @SerialName("mods") val modsCount: Long,
    @SerialName("categories") val categories: List<GameCategoryModel>
)
