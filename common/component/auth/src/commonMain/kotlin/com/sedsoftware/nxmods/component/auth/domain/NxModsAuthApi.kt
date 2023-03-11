package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo

internal interface NxModsAuthApi {
    fun validateApiKey(key: String): Observable<OwnProfile>
    fun getTracked(key: String): Observable<List<TrackingInfo>>
    fun getEndorsed(key: String): Observable<List<EndorsementInfo>>
}
