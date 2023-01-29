package com.sedsoftware.nxmods.component.home.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal class NxModsGameSwitcherManager(
    private val db: NxModsGameSwitcherDb,
    private val settings: NxModsGameSwitcherSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getActiveName(): Single<String> =
        singleOf(settings.selectedGameName)
            .subscribeOn(scheduler)

    fun getActiveDomain(): Single<String> =
        singleOf(settings.selectedGameDomain)
            .subscribeOn(scheduler)

    fun watchForBookmarkedGames(): Observable<List<GameInfo>> =
        db.observeGamesList()
            .map { games -> games.filter { it.isBookmarked }.sortedBy { it.name } }
            .subscribeOn(scheduler)

    fun switchTo(name: String, domain: String): Completable =
        completableFromFunction { storeGameData(name, domain) }
            .subscribeOn(scheduler)

    private fun storeGameData(name: String, domain: String) {
        settings.selectedGameName = name
        settings.selectedGameDomain = domain
    }
}
