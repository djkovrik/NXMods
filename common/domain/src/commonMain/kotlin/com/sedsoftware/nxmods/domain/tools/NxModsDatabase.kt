package com.sedsoftware.nxmods.domain.tools

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.TrackingInfo

interface NxModsDatabase {
    fun observeGamesList(): Observable<List<GameInfo>>
    fun observeGame(domain: String): Observable<GameInfo>
    fun observeBookmarkedGames(): Observable<List<GameInfo>>
    fun bookmark(domain: String, bookmark: Boolean): Completable
    fun saveGames(items: List<GameInfo>): Completable
    fun saveGame(item: GameInfo): Completable
    fun saveTracked(items: List<TrackingInfo>): Completable
    fun track(domain: String, modId: Long, track: Boolean): Completable
    fun saveEndorsed(items: List<EndorsementInfo>): Completable
    fun endorse(domain: String, modId: Long, endorse: Boolean): Completable
    fun getCachedModData(domain: String, modId: Long): Observable<CachedModData>
}
