package com.sedsoftware.nxmods.domain.tools

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo

interface NxModsApi {
    // Games
    fun getGames(): Observable<List<GameInfo>>
    fun getGame(domain: String): Observable<GameInfo>
    // Mods
    fun getLatestAdded(domain: String): Observable<List<ModInfo>>
    fun getLatestUpdated(domain: String): Observable<List<ModInfo>>
    fun getTrending(domain: String): Observable<List<ModInfo>>
    fun getMod(domain: String, id: Long): Observable<ModInfo>
    fun getChangelog(domain: String, id: Long): Observable<List<ChangelogItem>>
    // User
    fun validateApiKey(key: String): Observable<OwnProfile>
    fun getTracked(key: String): Observable<List<TrackingInfo>>
    fun track(domain: String, id: Long): Completable
    fun untrack(domain: String, id: Long): Completable
    fun getEndorsed(key: String): Observable<List<EndorsementInfo>>
    fun endorse(domain: String, id: Long, version: String): Completable
    fun unendorse(domain: String, id: Long, version: String): Completable
}
