package com.sedsoftware.nxmods.component.modlist.domain

import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface NxModsListDb {
    fun getActiveGameInfo(domain: String): Observable<GameInfo>
}
