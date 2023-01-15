package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class EndorsementInfoModel(
    @SerialName("mod_id") val modId: Long = 0L,
    @SerialName("domain_name") val domain: String = "",
    @SerialName("status") val status: String = "",
)
