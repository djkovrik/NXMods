package com.sedsoftware.nxmods.database

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.autoConnect
import com.badoo.reaktive.observable.firstOrError
import com.badoo.reaktive.observable.replay
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.asObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.singleOf
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.squareup.sqldelight.db.SqlDriver

class NxModsSharedDatabase(driver: Single<SqlDriver>): NxModsDatabase {

    constructor(driver: SqlDriver) : this(singleOf(driver))

    internal val queries: Single<NexusDatabaseQueries> =
        driver
            .map { NexusDatabase(it).nexusDatabaseQueries }
            .asObservable()
            .replay()
            .autoConnect()
            .firstOrError()

    override fun observeGamesList(): Observable<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun observeGame(domain: String): Observable<GameInfo> {
        TODO("Not yet implemented")
    }

    override fun observeBookmarkedGames(): Observable<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun bookmark(domain: String, isAdded: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun saveGames(items: List<GameInfo>): Completable {
        TODO("Not yet implemented")
    }

    override fun saveGame(item: GameInfo): Completable {
        TODO("Not yet implemented")
    }

    override fun searchByName(name: String): Maybe<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun saveTracked(items: List<TrackingInfo>): Completable {
        TODO("Not yet implemented")
    }

    override fun track(domain: String, id: Long, track: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun saveEndorsed(items: List<EndorsementInfo>): Completable {
        TODO("Not yet implemented")
    }

    override fun endorse(domain: String, id: Long, endorse: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun getCachedModData(domain: String, id: Long): Single<CachedModData> {
        TODO("Not yet implemented")
    }
}
