package com.sedsoftware.nxmods.component.modlist.domain

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.domain.type.ModListType

internal class NxModsListsManager(
    private val api: NxModsListsApi,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getModsList(type: ModListType): Observable<List<ModInfo>> =
        when (type) {
            ModListType.LATEST_ADDED -> api.getLatestAdded(settings.currentGameDomain)
            ModListType.LATEST_UPDATED -> api.getLatestAdded(settings.currentGameDomain)
            ModListType.TRENDING -> api.getLatestAdded(settings.currentGameDomain)
        }
            .subscribeOn(scheduler)

}
