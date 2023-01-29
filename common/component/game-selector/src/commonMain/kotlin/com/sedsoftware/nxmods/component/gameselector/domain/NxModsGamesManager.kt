package com.sedsoftware.nxmods.component.gameselector.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.doOnAfterNext
import com.badoo.reaktive.observable.flatMapCompletable
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

internal class NxModsGamesManager(
    private val api: NxModsGamesApi,
    private val db: NxModsGamesDb,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun observeGamesList(): Observable<List<GameInfo>> =
        db.observeGamesList()
            .doOnAfterNext { games ->
                val bookmarked = games.filter { it.isBookmarked }.sortedBy { it.domain }
                val game = bookmarked.firstOrNull()
                settings.currentGameName = game?.name.orEmpty()
                settings.currentGameDomain = game?.domain.orEmpty()
            }
            .subscribeOn(scheduler)

    fun fetchGamesList(): Completable =
        api.getGames()
            .flatMapCompletable(db::saveGames)
            .subscribeOn(scheduler)

    fun toggleBookmark(domain: String): Completable =
        db.toggleBookmark(domain)
            .subscribeOn(scheduler)
}
