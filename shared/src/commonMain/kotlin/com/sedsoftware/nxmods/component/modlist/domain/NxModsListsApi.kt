package com.sedsoftware.nxmods.component.modlist.domain

import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.ModInfo

internal interface NxModsListsApi {
    fun getLatestAdded(domain: String): Observable<List<ModInfo>>
    fun getLatestUpdated(domain: String): Observable<List<ModInfo>>
    fun getTrending(domain: String): Observable<List<ModInfo>>
}
