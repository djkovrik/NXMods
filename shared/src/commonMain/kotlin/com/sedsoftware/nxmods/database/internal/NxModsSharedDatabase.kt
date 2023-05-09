package com.sedsoftware.nxmods.database.internal

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.autoConnect
import com.badoo.reaktive.observable.combineLatest
import com.badoo.reaktive.observable.firstOrError
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.replay
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.asObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.singleOf
import com.sedsoftware.nxmods.database.GameInfoEntity
import com.sedsoftware.nxmods.database.NexusDatabase
import com.sedsoftware.nxmods.database.NexusDatabaseQueries
import com.sedsoftware.nxmods.database.mappers.GameInfoEntityMappers.gameInfoListToDomain
import com.sedsoftware.nxmods.database.mappers.GameInfoEntityMappers.gameInfoToDomain
import com.sedsoftware.nxmods.database.serializer.ModCategorySerializable.Companion.asModCategories
import com.sedsoftware.nxmods.database.serializer.ModCategorySerializable.Companion.asString
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModCategory
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.squareup.sqldelight.db.SqlDriver
import kotlin.math.abs

internal class NxModsSharedDatabase(driver: Single<SqlDriver>) : NxModsDatabase {

    constructor(driver: SqlDriver) : this(singleOf(driver))

    internal val queries: Single<NexusDatabaseQueries> =
        driver
            .map { NexusDatabase(it).nexusDatabaseQueries }
            .asObservable()
            .replay()
            .autoConnect()
            .firstOrError()

    override fun observeGamesList(): Observable<List<GameInfo>> =
        query(NexusDatabaseQueries::selectAllGames)
            .observe { it.executeAsList() }
            .map(gameInfoListToDomain)

    override fun observeGame(domain: String): Observable<GameInfo> =
        query { it.selectGame(domain) }
            .observe { it.executeAsOne() }
            .map(gameInfoToDomain)

    override fun observeBookmarkedGames(): Observable<List<GameInfo>> =
        query(NexusDatabaseQueries::selectBookmarkedGames)
            .observe { it.executeAsList() }
            .map(gameInfoListToDomain)

    override fun toggleBookmark(domain: String): Completable =
        execute { query ->
            query.transaction {
                val localGameInfo = query.selectGame(domain).executeAsOne()
                val newBookmarkState = abs(localGameInfo.bookmarked - 1L)
                query.bookmarkGame(newBookmarkState, domain)
            }
        }

    override fun saveGames(items: List<GameInfo>): Completable =
        execute { query ->
            query.transaction {
                items.forEach { item ->
                    query.insertGame(
                        id = item.id,
                        name = item.name,
                        bookmarked = item.isBookmarked.asLong(),
                        forumUrl = item.forumUrl,
                        nexusmodsUrl = item.nexusmodsUrl,
                        genre = item.genre,
                        fileCount = item.filesCount,
                        downloadsCount = item.downloadsCount,
                        domain = item.domain,
                        fileViews = item.filesViews,
                        authors = item.authors,
                        fileEndorsements = item.fileEndorsements,
                        modsCount = item.modsCount,
                        categories = item.categories.asString()
                    )
                }
            }
        }

    override fun saveGame(item: GameInfo): Completable =
        execute { query ->
            query.insertGame(
                id = item.id,
                name = item.name,
                bookmarked = item.isBookmarked.asLong(),
                forumUrl = item.forumUrl,
                nexusmodsUrl = item.nexusmodsUrl,
                genre = item.genre,
                fileCount = item.filesCount,
                downloadsCount = item.downloadsCount,
                domain = item.domain,
                fileViews = item.filesViews,
                authors = item.authors,
                fileEndorsements = item.fileEndorsements,
                modsCount = item.modsCount,
                categories = item.categories.asString()
            )
        }

    override fun saveTracked(items: List<TrackingInfo>): Completable =
        execute { query ->
            query.transaction {
                items.forEach { item ->
                    query.insertTracked(item.modId, item.domain)
                }
            }
        }

    override fun track(domain: String, modId: Long, track: Boolean): Completable =
        execute { query ->
            if (track) {
                query.insertTracked(modId, domain)
            } else {
                query.deleteTracked(modId, domain)
            }
        }

    override fun saveEndorsed(items: List<EndorsementInfo>): Completable =
        execute { query ->
            query.transaction {
                items.forEach { item ->
                    query.insertEndorsed(item.modId, item.domain)
                }
            }
        }

    override fun endorse(domain: String, modId: Long, endorse: Boolean): Completable =
        execute { query ->
            if (endorse) {
                query.insertEndorsed(modId, domain)
            } else {
                query.deleteEndorsed(modId, domain)
            }
        }

    override fun getCachedModData(domain: String, modId: Long, categoryId: Long): Observable<CachedModData> =
        combineLatest(
            query { it.endorsedExists(modId, domain) }.observe { it.executeAsOne() },
            query { it.trackedExists(modId, domain) }.observe { it.executeAsOne() },
            query { it.selectGame(domain) }.observe { it.executeAsOne() }
        ) { endorsed: Boolean, tracked: Boolean, game: GameInfoEntity ->
            val modCategory = game.categories?.asModCategories()?.firstOrNull { it.id == categoryId }
                ?: ModCategory.empty()
            CachedModData(endorsed, tracked, modCategory)
        }

    private fun Boolean.asLong(): Long = if (this) 1L else 0L
}
