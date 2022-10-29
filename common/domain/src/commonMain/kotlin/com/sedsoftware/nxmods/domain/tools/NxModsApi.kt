package com.sedsoftware.nxmods.domain.tools

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.single.Single
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.GivenEndorsement
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackedMod

interface NxModsApi {
    // Mods
    fun getChangelog(domain: String, id: Long): Single<List<ChangelogItem>>
    fun getLatestAdded(domain: String): Single<List<ModInfo>>
    fun getLatestUpdated(domain: String): Single<List<ModInfo>>
    fun getTrending(domain: String): Single<List<ModInfo>>
    fun getMod(domain: String, id: Long): Single<ModInfo>
    // Games
    fun getGame(domain: String): Single<GameInfo>
    fun getGames(): Single<List<GameInfo>>
    // User
    fun validateApiKey(): Single<OwnProfile>
    fun getTracked(): Single<List<TrackedMod>>
    fun track(domain: String, id: Long): Completable
    fun untrack(domain: String, id: Long): Completable
    fun getEndorsed(): Single<List<GivenEndorsement>>
    fun endorse(domain: String, id: Long): Completable
    fun unendorse(domain: String, id: Long): Completable
}
