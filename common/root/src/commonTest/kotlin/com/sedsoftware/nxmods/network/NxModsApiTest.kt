package com.sedsoftware.nxmods.network

import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.single.blockingGet
import com.badoo.reaktive.single.subscribeOn
import com.badoo.reaktive.test.base.assertNotError
import com.badoo.reaktive.test.scheduler.TestScheduler
import com.badoo.reaktive.test.single.TestSingleObserver
import com.badoo.reaktive.test.single.test
import com.sedsoftware.nxmods.Stubs
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import kotlinx.datetime.Clock.System
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertTrue

class NxModsApiTest {

    private val api: NxModsApi = Stubs.api
    private val scheduler: Scheduler = TestScheduler()

    @Test
    fun getGames_test() {
        val gamesSub: TestSingleObserver<List<GameInfo>> = api.getGames()
            .subscribeOn(scheduler)
            .test()

        gamesSub.assertNotError()

        val games: List<GameInfo> = api.getGames()
            .subscribeOn(scheduler)
            .blockingGet()

        assertTrue(games.isNotEmpty(), "Games should not be empty")

        for (game in games) {
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
    }

    @Test
    fun getGame_test() {
        val gameSub: TestSingleObserver<GameInfo> = api.getGame("cyberpunk2077")
            .subscribeOn(scheduler)
            .test()

        gameSub.assertNotError()

        val game: GameInfo = api.getGame("cyberpunk2077")
            .subscribeOn(scheduler)
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
    fun getLatest_test() {
        val latestSub: TestSingleObserver<List<ModInfo>> = api.getLatestAdded("cyberpunk2077")
            .subscribeOn(scheduler)
            .test()

        latestSub.assertNotError()

        val latest: List<ModInfo> = api.getLatestAdded("cyberpunk2077")
            .subscribeOn(scheduler)
            .blockingGet()

        assertTrue(latest.isNotEmpty(), "Games list should not be empty")

        for (mod in latest) {
            with(mod) {
                assertTrue(modId > 0, "modId should be declared")
                assertTrue(gameId > 0, "gameId should be declared")
                assertTrue(modDownloads > 0, "modDownloads should be declared")
                assertTrue(modDownloadsUnique > 0, "modDownloadsUnique should be declared")
                assertTrue(uid > 0, "uid should be declared")
                assertTrue(categoryId > 0, "categoryId should be declared")
                assertTrue(endorsementCount > 0, "endorsementCount should be declared")
                assertTrue(createdTimestamp > 0, "createdTimestamp should be declared")
                assertTrue(updatedTimestamp > 0, "updatedTimestamp should be declared")
                assertTrue(createdTime != System.now().toLocalDateTime(TimeZone.currentSystemDefault()), "createdTime should be declared")
                assertTrue(updatedTime != System.now().toLocalDateTime(TimeZone.currentSystemDefault()), "updatedTime should be declared")
                assertTrue(domainName.isNotEmpty(), "domainName should be declared")
                assertTrue(name.isNotEmpty(), "name should be declared")
                assertTrue(summary.isNotEmpty(), "summary should be declared")
                assertTrue(version.isNotEmpty(), "version should be declared")
                assertTrue(description.isNotEmpty(), "description should be declared")
                assertTrue(pictureUrl.isNotEmpty(), "pictureUrl should be declared")
                assertTrue(author.isNotEmpty(), "author should be declared")
                assertTrue(uploadedBy.isNotEmpty(), "uploadedBy should be declared")
                assertTrue(uploaderProfileUrl.isNotEmpty(), "uploaderProfileUrl should be declared")
                assertTrue(status.isNotEmpty(), "status should be declared")
            }
        }
    }

    @Test
    fun getMod_test() {
        val modSub: TestSingleObserver<ModInfo> = api.getMod("cyberpunk2077", 123)
            .subscribeOn(scheduler)
            .test()

        modSub.assertNotError()

        val mod: ModInfo = api.getMod("cyberpunk2077", 123)
            .subscribeOn(scheduler)
            .blockingGet()

        with(mod) {
            assertTrue(modId > 0, "modId should be declared")
            assertTrue(gameId > 0, "gameId should be declared")
            assertTrue(modDownloads > 0, "modDownloads should be declared")
            assertTrue(modDownloadsUnique > 0, "modDownloadsUnique should be declared")
            assertTrue(uid > 0, "uid should be declared")
            assertTrue(categoryId > 0, "categoryId should be declared")
            assertTrue(endorsementCount > 0, "endorsementCount should be declared")
            assertTrue(createdTimestamp > 0, "createdTimestamp should be declared")
            assertTrue(updatedTimestamp > 0, "updatedTimestamp should be declared")
            assertTrue(createdTime != System.now().toLocalDateTime(TimeZone.currentSystemDefault()), "createdTime should be declared")
            assertTrue(updatedTime != System.now().toLocalDateTime(TimeZone.currentSystemDefault()), "updatedTime should be declared")
            assertTrue(domainName.isNotEmpty(), "domainName should be declared")
            assertTrue(name.isNotEmpty(), "name should be declared")
            assertTrue(summary.isNotEmpty(), "summary should be declared")
            assertTrue(version.isNotEmpty(), "version should be declared")
            assertTrue(description.isNotEmpty(), "description should be declared")
            assertTrue(pictureUrl.isNotEmpty(), "pictureUrl should be declared")
            assertTrue(author.isNotEmpty(), "author should be declared")
            assertTrue(uploadedBy.isNotEmpty(), "uploadedBy should be declared")
            assertTrue(uploaderProfileUrl.isNotEmpty(), "uploaderProfileUrl should be declared")
            assertTrue(status.isNotEmpty(), "status should be declared")
        }
    }

    @Test
    fun getChangelog_test() {
        val changelogSub: TestSingleObserver<List<ChangelogItem>> = api.getChangelog("cyberpunk2077", 123)
            .subscribeOn(scheduler)
            .test()

        changelogSub.assertNotError()

        val changelog: List<ChangelogItem> = api.getChangelog("cyberpunk2077", 123)
            .subscribeOn(scheduler)
            .blockingGet()

        assertTrue(changelog.isNotEmpty(), "Changelog should not be empty")

        for (item in changelog) {
            with(item) {
                assertTrue(version.isNotEmpty(), "Version should be declared")
                assertTrue(changes.isNotEmpty(), "Changes should be declared")
            }
        }
    }

    @Test
    fun validateApiKey_test() {
        val validateSub: TestSingleObserver<OwnProfile> = api.validateApiKey("key")
            .subscribeOn(scheduler)
            .test()

        validateSub.assertNotError()

        val validated: OwnProfile = api.validateApiKey("123")
            .subscribeOn(scheduler)
            .blockingGet()

        with(validated) {
            assertTrue(userId > 0, "userId should be declared")
            assertTrue(key.isNotEmpty(), "key should be declared")
            assertTrue(name.isNotEmpty(), "name should be declared")
            assertTrue(email.isNotEmpty(), "email should be declared")
            assertTrue(avatar.isNotEmpty(), "avatar should be declared")
        }
    }

    @Test
    fun getTracked_test() {
        val trackedSub: TestSingleObserver<List<TrackingInfo>> = api.getTracked()
            .subscribeOn(scheduler)
            .test()

        trackedSub.assertNotError()

        val tracked: List<TrackingInfo> = api.getTracked()
            .subscribeOn(scheduler)
            .blockingGet()

        for (item in tracked) {
            with(item) {
                assertTrue(modId > 0, "modId should be declared")
                assertTrue(domain.isNotEmpty(), "domain should be declared")
            }
        }
    }

    @Test
    fun getEndorsed() {
        val endorsedSub: TestSingleObserver<List<EndorsementInfo>> = api.getEndorsed()
            .subscribeOn(scheduler)
            .test()

        endorsedSub.assertNotError()

        val endorsed: List<EndorsementInfo> = api.getEndorsed()
            .subscribeOn(scheduler)
            .blockingGet()

        for (item in endorsed) {
            with(item) {
                assertTrue(modId > 0, "modId should be declared")
                assertTrue(domain.isNotEmpty(), "domain should be declared")
            }
        }
    }
}
