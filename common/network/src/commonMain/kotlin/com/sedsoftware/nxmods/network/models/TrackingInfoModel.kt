package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TrackingInfoModel(
    @SerialName("mod_id") val domain: String,
    @SerialName("domain_name") val modId: Long
)
