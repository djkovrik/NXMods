package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.doOnAfterComplete
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.flatMapCompletable
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase

class EndorsementsManager(
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val scheduler: Scheduler = ioScheduler
) {

    fun fetchAllEndorsements(): Completable =
        api.getEndorsed()
            .flatMapCompletable(db::saveEndorsed)
            .subscribeOn(scheduler)

    fun endorse(domain: String, id: Long, version: String): Completable =
        api.endorse(domain, id, version)
            .doOnAfterComplete { db.endorse(domain, id, true) }
            .subscribeOn(scheduler)

    fun unendorse(domain: String, id: Long, version: String): Completable =
        api.unendorse(domain, id, version)
            .doOnAfterComplete { db.endorse(domain, id, false) }
            .subscribeOn(scheduler)
}
