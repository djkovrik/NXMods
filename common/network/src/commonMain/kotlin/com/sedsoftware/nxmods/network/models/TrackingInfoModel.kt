package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class TrackingInfoModel(
    @SerialName("mod_id") val modId: Long,
    @SerialName("domain_name") val domain: String
)
