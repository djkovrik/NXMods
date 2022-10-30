package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

class ModInfoManager(
    private val api: NxModsApi,
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
        api.getMod(domain, id)
            .subscribeOn(scheduler)

    fun getModChangelog(domain: String, id: Long): Single<List<ChangelogItem>> =
        api.getChangelog(domain, id)
            .subscribeOn(scheduler)
}
