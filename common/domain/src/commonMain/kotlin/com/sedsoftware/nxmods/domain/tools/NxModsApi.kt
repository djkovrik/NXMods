package com.sedsoftware.nxmods.domain.tools

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.single.Single
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo

interface NxModsApi {
    // Games
    fun getGames(): Single<List<GameInfo>>
    fun getGame(domain: String): Single<GameInfo>
    // Mods
    fun getLatestAdded(domain: String): Single<List<ModInfo>>
    fun getLatestUpdated(domain: String): Single<List<ModInfo>>
    fun getTrending(domain: String): Single<List<ModInfo>>
    fun getMod(domain: String, id: Long): Single<ModInfo>
    fun getChangelog(domain: String, id: Long): Single<List<ChangelogItem>>
    // User
    fun validateApiKey(key: String): Single<OwnProfile>
    fun getTracked(): Single<List<TrackingInfo>>
    fun track(domain: String, id: Long): Completable
    fun untrack(domain: String, id: Long): Completable
    fun getEndorsed(): Single<List<EndorsementInfo>>
    fun endorse(domain: String, id: Long, version: String): Completable
    fun unendorse(domain: String, id: Long, version: String): Completable
}
