package com.sedsoftware.nxmods.component.home.domain

import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface NxModsGameSwitcherDb {
    fun observeGamesList(): Observable<List<GameInfo>>
}
