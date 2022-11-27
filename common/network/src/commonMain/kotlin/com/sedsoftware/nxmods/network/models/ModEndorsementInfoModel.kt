package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ModEndorsementInfoModel(
    @SerialName("endorse_status") val status: String,
)
