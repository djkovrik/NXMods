package com.sedsoftware.nxmods.component.modinfo.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.ModInfo

internal interface NxModsInfoApi {
    fun getMod(domain: String, id: Long): Observable<ModInfo>
    fun track(domain: String, id: Long): Completable
    fun untrack(domain: String, id: Long): Completable
    fun endorse(domain: String, id: Long, version: String): Completable
    fun unendorse(domain: String, id: Long, version: String): Completable
    fun getChangelog(domain: String, id: Long): Observable<List<ChangelogItem>>
}
