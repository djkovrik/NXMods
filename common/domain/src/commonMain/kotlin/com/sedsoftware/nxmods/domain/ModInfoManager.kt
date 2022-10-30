package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.flatMap
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

class ModInfoManager(
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    private val domain: String
        get() = settings.currentDomain

    fun getLatestAddedMods(): Single<List<ModInfo>> =
        api.getLatestAdded(domain)
            .subscribeOn(scheduler)

    fun getLatestUpdatedMods(): Single<List<ModInfo>> =
        api.getLatestUpdated(domain)
            .subscribeOn(scheduler)

    fun getTrendingMods(): Single<List<ModInfo>> =
        api.getTrending(domain)
            .subscribeOn(scheduler)

    fun getModInfo(domain: String, id: Long): Single<ModInfo> =
        db.getCachedModData(domain, id)
            .flatMap { mapWithCached(domain, id, it) }
            .subscribeOn(scheduler)

    fun getModChangelog(domain: String, id: Long): Single<List<ChangelogItem>> =
        api.getChangelog(domain, id)
            .subscribeOn(scheduler)

    private fun mapWithCached(domain: String, id: Long, data: CachedModData) =
        api.getMod(domain, id)
            .map { item -> item.copy(isTracked = data.tracked, isEndorsed = data.endorsed) }
}
