package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.doOnAfterComplete
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.flatMapCompletable
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase

class TrackedManager(
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val scheduler: Scheduler = ioScheduler
) {

    fun fetchAllTracked(): Completable =
        api.getTracked()
            .flatMapCompletable(db::saveTracked)
            .subscribeOn(scheduler)

    fun track(domain: String, id: Long): Completable =
        api.track(domain, id)
            .doOnAfterComplete { db.track(domain, id, true) }
            .subscribeOn(scheduler)

    fun untrack(domain: String, id: Long): Completable =
        api.untrack(domain, id)
            .doOnAfterComplete { db.track(domain, id, false) }
            .subscribeOn(scheduler)
}
