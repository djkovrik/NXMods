package com.sedsoftware.nxmods.component.gameselector.domain

import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface NxModsGamesApi {
    fun getGames(): Observable<List<GameInfo>>
}
