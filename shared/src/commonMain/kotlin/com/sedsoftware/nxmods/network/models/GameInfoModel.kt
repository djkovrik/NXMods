package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class GameInfoModel(
    @SerialName("id") val id: Long = 0L,
    @SerialName("name") val name: String = "",
    @SerialName("forum_url") val forumUrl: String = "",
    @SerialName("nexusmods_url") val nexusmodsUrl: String = "",
    @SerialName("genre") val genre: String = "",
    @SerialName("file_count") val filesCount: Long = 0L,
    @SerialName("downloads") val downloadsCount: Long = 0L,
    @SerialName("domain_name") val domain: String = "",
    @SerialName("file_views") val filesViews: Long = 0L,
    @SerialName("authors") val authors: Long = 0L,
    @SerialName("file_endorsements") val fileEndorsements: Long = 0L,
    @SerialName("mods") val modsCount: Long = 0L,
    @SerialName("categories") val categories: List<GameCategoryModel> = emptyList()
)
