package com.sedsoftware.nxmods.network

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.singleOf
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.network.framework.CompletableSubject
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

class NxModsTestApi(
    private val scheduler: Scheduler
) : NxModsApi {

    val endorseSubject: CompletableSubject = CompletableSubject()
    val trackSubject: CompletableSubject = CompletableSubject()

    override fun getGames(): Single<List<GameInfo>> =
        singleOf(GetGames.response)
            .observeOn(scheduler)
            .map(gameInfoModelListToDomain)

    override fun getGame(domain: String): Single<GameInfo> =
        singleOf(GetGame.response)
            .observeOn(scheduler)
            .map(gameInfoModelToDomain)

    override fun getLatestAdded(domain: String): Single<List<ModInfo>> =
        singleOf(GetModsList.response)
            .observeOn(scheduler)
            .map(modInfoListToDomain)

    override fun getLatestUpdated(domain: String): Single<List<ModInfo>> =
        singleOf(GetModsList.response)
            .observeOn(scheduler)
            .map(modInfoListToDomain)

    override fun getTrending(domain: String): Single<List<ModInfo>> =
        singleOf(GetModsList.response)
            .observeOn(scheduler)
            .map(modInfoListToDomain)

    override fun getMod(domain: String, id: Long): Single<ModInfo> =
        singleOf(GetMod.response)
            .observeOn(scheduler)
            .map(modInfoToDomain)

    override fun getChangelog(domain: String, id: Long): Single<List<ChangelogItem>> =
        singleOf(GetChangelog.response)
            .observeOn(scheduler)
            .map(changelogToDomain)

    override fun validateApiKey(key: String): Single<OwnProfile> =
        singleOf(ValidateApiKey.response)
            .observeOn(scheduler)
            .map(profileToDomain)

    override fun getTracked(): Single<List<TrackingInfo>> =
        singleOf(GetTracked.response)
            .observeOn(scheduler)
            .map(trackingInfoListToDomain)

    override fun track(domain: String, id: Long): Completable =
        trackSubject
            .observeOn(scheduler)

    override fun untrack(domain: String, id: Long): Completable =
        trackSubject
            .observeOn(scheduler)

    override fun getEndorsed(): Single<List<EndorsementInfo>> =
        singleOf(GetEndorsed.response)
            .observeOn(scheduler)
            .map(endorsementInfoListToDomain)

    override fun endorse(domain: String, id: Long, version: String): Completable =
        endorseSubject
            .observeOn(scheduler)

    override fun unendorse(domain: String, id: Long, version: String): Completable =
        endorseSubject
            .observeOn(scheduler)
}
