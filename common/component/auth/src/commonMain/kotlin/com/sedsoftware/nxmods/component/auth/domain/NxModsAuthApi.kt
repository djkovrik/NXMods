package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.OwnProfile

internal interface NxModsAuthApi {
    fun validateApiKey(key: String): Observable<OwnProfile>
}
