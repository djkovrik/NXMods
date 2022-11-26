package com.sedsoftware.nxmods.network

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.single.Single
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi

class NxModsSharedApi : NxModsApi {

    override fun getGames(): Single<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun getGame(domain: String): Single<GameInfo> {
        TODO("Not yet implemented")
    }

    override fun getLatestAdded(domain: String): Single<List<ModInfo>> {
        TODO("Not yet implemented")
    }

    override fun getLatestUpdated(domain: String): Single<List<ModInfo>> {
        TODO("Not yet implemented")
    }

    override fun getTrending(domain: String): Single<List<ModInfo>> {
        TODO("Not yet implemented")
    }

    override fun getMod(domain: String, id: Long): Single<ModInfo> {
        TODO("Not yet implemented")
    }

    override fun getChangelog(domain: String, id: Long): Single<List<ChangelogItem>> {
        TODO("Not yet implemented")
    }

    override fun validateApiKey(key: String): Single<OwnProfile> {
        TODO("Not yet implemented")
    }

    override fun getTracked(): Single<List<TrackingInfo>> {
        TODO("Not yet implemented")
    }

    override fun track(domain: String, id: Long): Completable {
        TODO("Not yet implemented")
    }

    override fun untrack(domain: String, id: Long): Completable {
        TODO("Not yet implemented")
    }

    override fun getEndorsed(): Single<List<EndorsementInfo>> {
        TODO("Not yet implemented")
    }

    override fun endorse(domain: String, id: Long): Completable {
        TODO("Not yet implemented")
    }

    override fun unendorse(domain: String, id: Long): Completable {
        TODO("Not yet implemented")
    }

}
