package com.sedsoftware.nxmods.component.gameselector.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.flatMapCompletable
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal class NxModsGamesManager(
    private val api: NxModsGamesApi,
    private val db: NxModsGamesDb,
    private val scheduler: Scheduler = ioScheduler
) {

    fun observeGamesList(): Observable<List<GameInfo>> =
        db.observeGamesList()
            .subscribeOn(scheduler)

    fun fetchGamesList(): Completable =
        api.getGames()
            .flatMapCompletable(db::saveGames)
            .subscribeOn(scheduler)

    fun toggleBookmark(domain: String): Completable =
        db.toggleBookmark(domain)
            .subscribeOn(scheduler)
}
