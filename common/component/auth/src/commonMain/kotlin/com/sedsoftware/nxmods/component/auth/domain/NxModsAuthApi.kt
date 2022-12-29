package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.single.Single
import com.sedsoftware.nxmods.domain.entity.OwnProfile

internal interface NxModsAuthApi {
    fun validateApiKey(key: String): Single<OwnProfile>
}
