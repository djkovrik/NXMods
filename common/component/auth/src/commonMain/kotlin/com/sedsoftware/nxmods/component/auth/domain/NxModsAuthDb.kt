package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.completable.Completable
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.TrackingInfo

internal interface NxModsAuthDb {
    fun saveEndorsed(items: List<EndorsementInfo>): Completable
    fun saveTracked(items: List<TrackingInfo>): Completable
}
