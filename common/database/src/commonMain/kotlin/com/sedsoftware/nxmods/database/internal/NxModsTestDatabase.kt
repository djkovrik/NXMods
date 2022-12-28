package com.sedsoftware.nxmods.database.internal

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.notNull
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.trampolineScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.subject.behavior.BehaviorSubject
import com.sedsoftware.nxmods.database.EndorsementInfoEntity
import com.sedsoftware.nxmods.database.GameInfoEntity
import com.sedsoftware.nxmods.database.TrackingInfoEntity
import com.sedsoftware.nxmods.database.mappers.GameInfoEntityMappers.gameInfoToDomain
import com.sedsoftware.nxmods.database.serializer.GameCategorySerializable.Companion.asString
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.type.EndorseStatus

internal class NxModsTestDatabase(
    private val scheduler: Scheduler = trampolineScheduler
) : NxModsDatabase {

    private val gameInfoSubject: BehaviorSubject<Map<Long, GameInfoEntity>> = BehaviorSubject(emptyMap())
    private val trackingInfoSubject: BehaviorSubject<Map<Long, TrackingInfoEntity>> = BehaviorSubject(emptyMap())
    private val endorsementInfoSubject: BehaviorSubject<Map<Long, EndorsementInfoEntity>> = BehaviorSubject(emptyMap())

    private val gameInfoObservable: Observable<Map<Long, GameInfoEntity>> = gameInfoSubject.observeOn(scheduler)

    private val testing: Testing = Testing()

    override fun observeGamesList(): Observable<List<GameInfo>> =
        gameInfoObservable
            .map { entry: Map<Long, GameInfoEntity> ->
                entry.values
                    .map(gameInfoToDomain)
                    .toList()
            }

    override fun observeGame(domain: String): Observable<GameInfo> =
        gameInfoObservable
            .map { entry: Map<Long, GameInfoEntity> ->
                entry.values
                    .firstOrNull { it.domain == domain }
            }
            .notNull()
            .map(gameInfoToDomain)

    override fun observeBookmarkedGames(): Observable<List<GameInfo>> =
        gameInfoObservable
            .map { entry: Map<Long, GameInfoEntity> ->
                entry.values
                    .filter { it.bookmarked == 1L }
                    .map(gameInfoToDomain)
                    .toList()
            }

    override fun bookmark(domain: String, bookmark: Boolean): Completable =
        execute { testing.bookmarkGame(domain, bookmark) }

    override fun saveGames(items: List<GameInfo>): Completable =
        execute { items.forEach { testing.addGameInfo(it) } }

    override fun saveGame(item: GameInfo): Completable =
        execute { testing.addGameInfo(item) }

    override fun saveTracked(items: List<TrackingInfo>): Completable =
        execute { items.forEach { testing.addTrackedInfo(it) } }

    override fun track(domain: String, modId: Long, track: Boolean): Completable =
        execute { testing.trackItem(domain, modId, track) }

    override fun saveEndorsed(items: List<EndorsementInfo>): Completable =
        execute { items.forEach { testing.addEndorsedInfo(it) } }

    override fun endorse(domain: String, modId: Long, endorse: Boolean): Completable =
        execute { testing.endorseItem(domain, modId, endorse) }

    override fun getCachedModData(domain: String, modId: Long): Single<CachedModData> =
        singleFromFunction { testing.getGameUserState(domain, modId) }
            .observeOn(scheduler)

    private fun execute(block: () -> Unit): Completable =
        completableFromFunction(block)
            .observeOn(scheduler)

    inner class Testing {

        fun bookmarkGame(domain: String, bookmark: Boolean) {
            val state = if (bookmark) 1L else 0L
            val targetEntry = gameInfoSubject.value.values.firstOrNull { it.domain == domain } ?: return
            val databaseId = gameInfoSubject.value.entries.find { it.value.id == targetEntry.id }?.key ?: return
            updateGameInfoItem(id = databaseId) { it.copy(bookmarked = state) }
        }

        fun endorseItem(domain: String, modId: Long, endorse: Boolean) {
            if (endorse) {
                addEndorsedInfo(
                    EndorsementInfo(
                        modId = modId,
                        domain = domain,
                        status = EndorseStatus.ENDORSED
                    )
                )
            } else {
                deleteEndorsed(domain, modId)
            }
        }

        fun trackItem(domain: String, modId: Long, track: Boolean) {
            if (track) {
                addTrackedInfo(
                    TrackingInfo(
                        domain = domain,
                        modId = modId
                    )
                )
            } else {
                deleteTracked(domain, modId)
            }
        }

        fun addTrackedInfo(info: TrackingInfo) {
            updateTrackingInfoItems { items ->
                val nextId = items.keys.maxOrNull()?.plus(1L) ?: 1L
                val item = TrackingInfoEntity(
                    id = nextId,
                    modId = info.modId,
                    domain = info.domain
                )
                items + (nextId to item)
            }
        }

        fun addEndorsedInfo(info: EndorsementInfo) {
            updateEndorsementInfoItems { items ->
                val nextId = items.keys.maxOrNull()?.plus(1L) ?: 1L
                val item = EndorsementInfoEntity(
                    id = nextId,
                    modId = info.modId,
                    domain = info.domain
                )
                items + (nextId to item)
            }
        }

        fun addGameInfo(info: GameInfo) {
            updateGameInfoItems { items ->
                val nextId = items.keys.maxOrNull()?.plus(1L) ?: 1L
                val item = GameInfoEntity(
                    id = info.id,
                    name = info.name,
                    bookmarked = if (info.isBookmarked) 1L else 0L,
                    forumUrl = info.forumUrl,
                    nexusmodsUrl = info.nexusmodsUrl,
                    genre = info.genre,
                    fileCount = info.filesCount,
                    downloadsCount = info.downloadsCount,
                    domain = info.domain,
                    fileViews = info.filesViews,
                    authors = info.authors,
                    fileEndorsements = info.fileEndorsements,
                    modsCount = info.modsCount,
                    categories = info.categories.asString()
                )
                items + (nextId to item)
            }
        }

        fun getByName(name: String): List<GameInfoEntity>? =
            gameInfoSubject.value.values
                .filter { it.name.contains(name) }
                .takeIf { it.isNotEmpty() }

        fun getGameUserState(domain: String, modId: Long): CachedModData {
            val endorsedEntry = endorsementInfoSubject.value.values.firstOrNull { it.domain == domain && it.modId == modId }
            val trackedEntry = trackingInfoSubject.value.values.firstOrNull { it.domain == domain && it.modId == modId }

            return CachedModData(
                endorsed = endorsedEntry != null,
                tracked = trackedEntry != null
            )
        }

        private fun deleteEndorsed(domain: String, modId: Long) {
            val targetEntry = endorsementInfoSubject.value.values.firstOrNull { it.domain == domain && it.modId == modId } ?: return
            val targetId = targetEntry.id
            updateEndorsementInfoItems { it - targetId }
        }

        private fun deleteTracked(domain: String, id: Long) {
            val targetEntry = trackingInfoSubject.value.values.firstOrNull { it.domain == domain && it.id == id } ?: return
            val targetId = targetEntry.id
            updateTrackingInfoItems { it - targetId }
        }

        private fun updateGameInfoItems(func: (Map<Long, GameInfoEntity>) -> Map<Long, GameInfoEntity>) {
            gameInfoSubject.onNext(func(gameInfoSubject.value))
        }

        private fun updateGameInfoItem(id: Long, func: (GameInfoEntity) -> GameInfoEntity) {
            updateGameInfoItems {
                it + (id to it.getValue(id).let(func))
            }
        }

        private fun updateTrackingInfoItems(func: (Map<Long, TrackingInfoEntity>) -> Map<Long, TrackingInfoEntity>) {
            trackingInfoSubject.onNext(func(trackingInfoSubject.value))
        }

        private fun updateEndorsementInfoItems(func: (Map<Long, EndorsementInfoEntity>) -> Map<Long, EndorsementInfoEntity>) {
            endorsementInfoSubject.onNext(func(endorsementInfoSubject.value))
        }
    }
}
