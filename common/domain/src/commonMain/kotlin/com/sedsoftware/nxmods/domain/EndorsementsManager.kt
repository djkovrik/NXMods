package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.flatMapCompletable
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

    fun endorse(domain: String, id: Long): Completable =
        api.endorse(domain, id)
            .subscribeOn(scheduler)

    fun unendorse(domain: String, id: Long): Completable =
        api.unendorse(domain, id)
            .subscribeOn(scheduler)
}
