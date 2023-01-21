package com.sedsoftware.nxmods.component.gameselector.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface NxModsGamesDb {
    fun observeGamesList(): Observable<List<GameInfo>>
    fun saveGames(items: List<GameInfo>): Completable
    fun toggleBookmark(domain: String): Completable
}
