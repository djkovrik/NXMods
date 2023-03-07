package com.sedsoftware.nxmods.component.modlist.domain

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.domain.type.ModListType

internal class NxModsListsManager(
    private val api: NxModsListsApi,
    private val db: NxModsListDb,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getActiveGameInfo(): Observable<GameInfo> =
        db.getActiveGameInfo(settings.currentGameDomain)

    fun getModsList(type: ModListType): Observable<List<ModInfo>> =
        when (type) {
            ModListType.LATEST_ADDED -> api.getLatestAdded(settings.currentGameDomain)
            ModListType.LATEST_UPDATED -> api.getLatestUpdated(settings.currentGameDomain)
            ModListType.TRENDING -> api.getTrending(settings.currentGameDomain)
        }
            .map { mods ->
                if (settings.allowNsfw) {
                    mods
                } else {
                    mods.filter { !it.containsAdultContent }
                }
            }
            .subscribeOn(scheduler)

}
