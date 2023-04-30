package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.network.models.OwnProfileModel

internal object OwnProfileMapper {

    val profileToDomain: OwnProfileModel.() -> OwnProfile = {
        OwnProfile(
            userId = userId,
            key = key,
            name = name,
            isPremium = isPremium,
            isSupporter = isSupporter,
            email = email,
            avatar = avatar
        )
    }
}
