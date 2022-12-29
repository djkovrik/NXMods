package com.sedsoftware.nxmods.database

import com.badoo.reaktive.completable.blockingAwait
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.observable.firstOrError
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.single.blockingGet
import com.badoo.reaktive.single.subscribeOn
import com.badoo.reaktive.test.scheduler.TestScheduler
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.framework.CompletableSubject
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.type.EndorseStatus
import com.sedsoftware.nxmods.network.NetworkComponentTestDependencies
import com.sedsoftware.nxmods.network.NetworkFeatureComponentMock
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NxModsDatabaseTest {

    private val endorseSubject = CompletableSubject()
    private val trackSubject = CompletableSubject()

    private val api: NxModsApi
        get() {
            val component = NetworkFeatureComponentMock(
                dependencies = object : NetworkComponentTestDependencies {
                    override val endorse: CompletableSubject = endorseSubject
                    override val track: CompletableSubject = trackSubject
                }
            )
            return component.api
        }

    private val database: NxModsDatabase by lazy {
        val component = DatabaseFeatureComponentMock()
        component.database
    }

    private val scheduler: Scheduler = TestScheduler()

    @Test
    fun saveGames_test() {
        val games: List<GameInfo> = api.getGames()
            .firstOrError()
            .subscribeOn(scheduler)
            .blockingGet()

        database.saveGames(games)
            .subscribeOn(scheduler)
            .blockingAwait()

        val game: GameInfo = database.observeGame("morrowind")
            .subscribeOn(scheduler)
            .firstOrError()
            .blockingGet()

        with(game) {
            assertTrue(id > 0, "id should be declared")
            assertTrue(filesCount > 0, "filesCount should be declared")
            assertTrue(downloadsCount > 0, "downloadsCount should be declared")
            assertTrue(filesViews > 0, "filesViews should be declared")
            assertTrue(authors > 0, "authors should be declared")
            assertTrue(fileEndorsements > 0, "fileEndorsements should be declared")
            assertTrue(modsCount > 0, "modsCount should be declared")
            assertTrue(name.isNotEmpty(), "name should be declared")
            assertTrue(forumUrl.isNotEmpty(), "forumUrl should be declared")
            assertTrue(nexusmodsUrl.isNotEmpty(), "nexusmodsUrl should be declared")
            assertTrue(genre.isNotEmpty(), "genre should be declared")
            assertTrue(domain.isNotEmpty(), "domain should be declared")
        }
    }

    @Test
    fun bookmarking_test() {
        val games: List<GameInfo> = api.getGames()
            .firstOrError()
            .subscribeOn(scheduler)
            .blockingGet()

        database.saveGames(games)
            .subscribeOn(scheduler)
            .blockingAwait()

        val bookmarked: List<GameInfo> = database.observeBookmarkedGames()
            .subscribeOn(scheduler)
            .firstOrError()
            .blockingGet()

        assertTrue(bookmarked.isEmpty(), "No bookmarked games at this stage")

        database.bookmark("morrowind", true)
            .subscribeOn(scheduler)
            .blockingAwait()

        val bookmarked2: List<GameInfo> = database.observeBookmarkedGames()
            .subscribeOn(scheduler)
            .firstOrError()
            .blockingGet()

        assertTrue(bookmarked2.size == 1, "Have bookmarked game")
    }

    @Test
    fun cachedModData_test() {
        val tracked: List<TrackingInfo> = listOf(
            TrackingInfo("morrowind", 1),
            TrackingInfo("morrowind", 2),
            TrackingInfo("morrowind", 3),
        )

        val endorsed: List<EndorsementInfo> = listOf(
            EndorsementInfo(2, "morrowind", EndorseStatus.ENDORSED)
        )

        val empty: CachedModData = database.getCachedModData("morrowind", 1)
            .firstOrError()
            .subscribeOn(scheduler)
            .blockingGet()

        assertFalse(empty.tracked, "morrowind modId 1 - not tracked")
        assertFalse(empty.endorsed, "morrowind modId 1 - not endorsed")

        database.saveTracked(tracked)
            .subscribeOn(scheduler)
            .blockingAwait()

        database.saveEndorsed(endorsed)
            .subscribeOn(scheduler)
            .blockingAwait()

        val trackedButNotEndorsed: CachedModData = database.getCachedModData("morrowind", 1)
            .firstOrError()
            .subscribeOn(scheduler)
            .blockingGet()

        assertTrue(trackedButNotEndorsed.tracked, "morrowind modId 1 - tracked")
        assertFalse(trackedButNotEndorsed.endorsed, "morrowind modId 1 - not endorsed")

        database.endorse("morrowind", 1, true)
            .subscribeOn(scheduler)
            .blockingAwait()

        val trackedAndEndorsed: CachedModData = database.getCachedModData("morrowind", 1)
            .firstOrError()
            .subscribeOn(scheduler)
            .blockingGet()

        assertTrue(trackedAndEndorsed.tracked, "morrowind modId 1 - tracked")
        assertTrue(trackedAndEndorsed.endorsed, "morrowind modId 1 - endorsed")

        database.track("morrowind", 1, false)
            .subscribeOn(scheduler)
            .blockingAwait()

        val endorsedButNotTracked: CachedModData = database.getCachedModData("morrowind", 1)
            .firstOrError()
            .subscribeOn(scheduler)
            .blockingGet()

        assertFalse(endorsedButNotTracked.tracked, "morrowind modId 1 - not tracked")
        assertTrue(endorsedButNotTracked.endorsed, "morrowind modId 1 - endorsed")
    }
}
