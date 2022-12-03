package com.sedsoftware.nxmods.network.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TrackRequestBody(
    @SerialName("domain_name") val domain: String,
    @SerialName("mod_id") val id: Long
)
