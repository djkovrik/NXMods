package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class OwnProfileModel(
    @SerialName("user_id") val userId: Long,
    @SerialName("key") val key: String,
    @SerialName("name") val name: String,
    @SerialName("is_premium") val isPremium: Boolean,
    @SerialName("is_supporter") val isSupporter: Boolean,
    @SerialName("email") val email: String,
    @SerialName("profile_url") val avatar: String
)
