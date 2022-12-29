package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.flatMapCompletable
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase

class GamesManager(
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val scheduler: Scheduler = ioScheduler
) {

    fun observeGamesList(): Observable<List<GameInfo>> =
        db.observeGamesList()
            .subscribeOn(scheduler)

    fun fetchGamesList(): Completable =
        api.getGames()
            .flatMapCompletable(db::saveGames)
            .subscribeOn(scheduler)

    fun observeSelectedGame(domain: String): Observable<GameInfo> =
        db.observeGame(domain)
            .subscribeOn(scheduler)

    fun fetchSelectedGame(domain: String): Completable =
        api.getGame(domain)
            .flatMapCompletable(db::saveGame)
            .subscribeOn(scheduler)

    fun observeBookmarked(): Observable<List<GameInfo>> =
        db.observeBookmarkedGames()
            .subscribeOn(scheduler)

    fun bookmark(domain: String, isAdded: Boolean): Completable =
        db.bookmark(domain, isAdded)
            .subscribeOn(scheduler)
}
