package com.sedsoftware.nxmods.component.modinfo.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.CachedModData

internal interface NxModsInfoDb {
    fun track(domain: String, modId: Long, track: Boolean): Completable
    fun endorse(domain: String, modId: Long, endorse: Boolean): Completable
    fun getCachedModData(domain: String, modId: Long, categoryId: Long): Observable<CachedModData>
}
