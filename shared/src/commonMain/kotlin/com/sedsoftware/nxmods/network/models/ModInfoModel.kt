package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ModInfoModel(
    @SerialName("mod_id") val modId: Long = 0L,
    @SerialName("game_id") val gameId: Long = 0L,
    @SerialName("domain_name") val domainName: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("summary") val summary: String = "",
    @SerialName("version") val version: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("picture_url") val pictureUrl: String = "",
    @SerialName("mod_downloads") val modDownloads: Long = 0L,
    @SerialName("mod_unique_downloads") val modDownloadsUnique: Long = 0L,
    @SerialName("uid") val uid: Long = 0L,
    @SerialName("allow_rating") val allowRating: Boolean,
    @SerialName("category_id") val categoryId: Long = 0L,
    @SerialName("endorsement_count") val endorsementCount: Long = 0L,
    @SerialName("created_timestamp") val createdTimestamp: Long = 0L,
    @SerialName("updated_timestamp") val updatedTimestamp: Long = 0L,
    @SerialName("author") val author: String = "",
    @SerialName("uploaded_by") val uploadedBy: String = "",
    @SerialName("uploaded_users_profile_url") val uploaderProfileUrl: String = "",
    @SerialName("contains_adult_content") val containsAdultContent: Boolean,
    @SerialName("status") val status: String = "",
    @SerialName("available") val available: Boolean,
    @SerialName("user") val user: UserInfoModel,
    @SerialName("endorsement") val endorsement: ModEndorsementInfoModel? = null
)
