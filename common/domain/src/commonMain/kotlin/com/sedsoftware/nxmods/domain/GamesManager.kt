package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.maybe.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.doOnAfterSuccess
import com.badoo.reaktive.single.flatMapCompletable
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase

class GamesManager(
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val scheduler: Scheduler = ioScheduler
) {

    fun fetchAllGames(): Completable =
        api.getGames()
            .flatMapCompletable(db::saveGames)
            .subscribeOn(scheduler)

    fun searchByName(name: String): Maybe<List<GameInfo>> =
        db.searchByName(name)
            .subscribeOn(scheduler)

    fun getGameByDomain(domain: String): Single<GameInfo> =
        api.getGame(domain)
            .doOnAfterSuccess(db::saveGame)
            .subscribeOn(scheduler)
}
