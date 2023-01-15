package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class UserInfoModel(
    @SerialName("member_id") val memberId: Long = 0L,
    @SerialName("member_group_id") val memberGroupId: Int = 1,
    @SerialName("name") val name: String = ""
)
