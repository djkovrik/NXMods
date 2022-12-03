package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class GameCategoryModel(
    @SerialName("category_id") val id: Long,
    @SerialName("name") val name: String
)
