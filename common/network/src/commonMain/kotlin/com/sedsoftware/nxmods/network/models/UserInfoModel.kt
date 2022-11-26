package com.sedsoftware.nxmods.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserInfoModel(
    @SerialName("member_id") val memberId: Long,
    @SerialName("member_group_id") val memberGroupId: Int,
    @SerialName("name") val name: String
)
