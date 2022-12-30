package com.sedsoftware.nxmods.network.internal

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.observableOf
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.trampolineScheduler
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.framework.CompletableSubject
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.network.mappers.ChangelogMapper.changelogToDomain
import com.sedsoftware.nxmods.network.mappers.EndorsementInfoMapper.endorsementInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.GameInfoModelMapper.gameInfoModelListToDomain
import com.sedsoftware.nxmods.network.mappers.GameInfoModelMapper.gameInfoModelToDomain
import com.sedsoftware.nxmods.network.mappers.ModInfoModelMapper.modInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.ModInfoModelMapper.modInfoToDomain
import com.sedsoftware.nxmods.network.mappers.OwnProfileMapper.profileToDomain
import com.sedsoftware.nxmods.network.mappers.TrackingInfoMapper.trackingInfoListToDomain
import com.sedsoftware.nxmods.network.stubs.responses.GetChangelog
import com.sedsoftware.nxmods.network.stubs.responses.GetEndorsed
import com.sedsoftware.nxmods.network.stubs.responses.GetGame
import com.sedsoftware.nxmods.network.stubs.responses.GetGames
import com.sedsoftware.nxmods.network.stubs.responses.GetMod
import com.sedsoftware.nxmods.network.stubs.responses.GetModsList
import com.sedsoftware.nxmods.network.stubs.responses.GetTracked
import com.sedsoftware.nxmods.network.stubs.responses.ValidateApiKey

internal class NxModsTestApi(
    private val endorseSubject: CompletableSubject,
    private val trackSubject: CompletableSubject,
    private val scheduler: Scheduler = trampolineScheduler
) : NxModsApi {

    override fun getGames(): Observable<List<GameInfo>> =
        observableOf(GetGames.response)
            .observeOn(scheduler)
            .map(gameInfoModelListToDomain)

    override fun getGame(domain: String): Observable<GameInfo> =
        observableOf(GetGame.response)
            .observeOn(scheduler)
            .map(gameInfoModelToDomain)

    override fun getLatestAdded(domain: String): Observable<List<ModInfo>> =
        observableOf(GetModsList.response)
            .observeOn(scheduler)
            .map(modInfoListToDomain)

    override fun getLatestUpdated(domain: String): Observable<List<ModInfo>> =
        observableOf(GetModsList.response)
            .observeOn(scheduler)
            .map(modInfoListToDomain)

    override fun getTrending(domain: String): Observable<List<ModInfo>> =
        observableOf(GetModsList.response)
            .observeOn(scheduler)
            .map(modInfoListToDomain)

    override fun getMod(domain: String, id: Long): Observable<ModInfo> =
        observableOf(GetMod.response)
            .observeOn(scheduler)
            .map(modInfoToDomain)

    override fun getChangelog(domain: String, id: Long): Observable<List<ChangelogItem>> =
        observableOf(GetChangelog.response)
            .observeOn(scheduler)
            .map(changelogToDomain)

    override fun validateApiKey(key: String): Observable<OwnProfile> =
        observableOf(ValidateApiKey.response)
            .observeOn(scheduler)
            .map(profileToDomain)

    override fun getTracked(): Observable<List<TrackingInfo>> =
        observableOf(GetTracked.response)
            .observeOn(scheduler)
            .map(trackingInfoListToDomain)

    override fun track(domain: String, id: Long): Completable =
        trackSubject
            .observeOn(scheduler)

    override fun untrack(domain: String, id: Long): Completable =
        trackSubject
            .observeOn(scheduler)

    override fun getEndorsed(): Observable<List<EndorsementInfo>> =
        observableOf(GetEndorsed.response)
            .observeOn(scheduler)
            .map(endorsementInfoListToDomain)

    override fun endorse(domain: String, id: Long, version: String): Completable =
        endorseSubject
            .observeOn(scheduler)

    override fun unendorse(domain: String, id: Long, version: String): Completable =
        endorseSubject
            .observeOn(scheduler)
}
