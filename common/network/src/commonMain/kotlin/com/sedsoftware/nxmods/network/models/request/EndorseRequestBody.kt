package com.sedsoftware.nxmods.network.models.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class EndorseRequestBody(
    @SerialName("version") val version: String
)
