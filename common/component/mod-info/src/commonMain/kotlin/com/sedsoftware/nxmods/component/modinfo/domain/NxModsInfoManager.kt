package com.sedsoftware.nxmods.component.modinfo.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.doOnAfterComplete
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.flatMap
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.ModInfo

internal class NxModsInfoManager(
    private val api: NxModsInfoApi,
    private val db: NxModsInfoDb,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getModInfo(domain: String, id: Long, categoryId: Long): Observable<ModInfo> =
        db.getCachedModData(domain, id, categoryId)
            .flatMap { mapWithCached(domain, id, it) }
            .subscribeOn(scheduler)

    fun getModChangelog(domain: String, id: Long): Observable<List<ChangelogItem>> =
        api.getChangelog(domain, id)
            .subscribeOn(scheduler)

    fun endorse(domain: String, id: Long, version: String): Completable =
        api.endorse(domain, id, version)
            .doOnAfterComplete { db.endorse(domain, id, true) }
            .subscribeOn(scheduler)

    fun unendorse(domain: String, id: Long, version: String): Completable =
        api.unendorse(domain, id, version)
            .doOnAfterComplete { db.endorse(domain, id, false) }
            .subscribeOn(scheduler)

    fun track(domain: String, id: Long): Completable =
        api.track(domain, id)
            .doOnAfterComplete { db.track(domain, id, true) }
            .subscribeOn(scheduler)

    fun untrack(domain: String, id: Long): Completable =
        api.untrack(domain, id)
            .doOnAfterComplete { db.track(domain, id, false) }
            .subscribeOn(scheduler)

    private fun mapWithCached(domain: String, id: Long, data: CachedModData): Observable<ModInfo> =
        api.getMod(domain, id)
            .map { item ->
                item.copy(isTracked = data.tracked, isEndorsed = data.endorsed, categoryName = data.category.name)
            }
}
