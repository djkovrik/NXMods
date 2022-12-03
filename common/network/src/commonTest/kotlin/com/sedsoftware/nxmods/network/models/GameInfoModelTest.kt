package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.responses.GetGame
import com.sedsoftware.nxmods.network.stubs.responses.GetGames
import kotlin.test.Test
import kotlin.test.assertTrue

class GameInfoModelTest {

    @Test
    fun gameInfoModelSerialization_test() {
        val response: GameInfoModel = GetGame.response

        assertTrue(response.id != 0L, "id should be deserialized")
        assertTrue(response.name.isNotEmpty(), "name should be deserialized")
        assertTrue(response.forumUrl.isNotEmpty(), "forumUrl should be deserialized")
        assertTrue(response.nexusmodsUrl.isNotEmpty(), "nexusmodsUrl should be deserialized")
        assertTrue(response.genre.isNotEmpty(), "genre should be deserialized")
        assertTrue(response.filesCount != 0L, "filesCount should be deserialized")
        assertTrue(response.downloadsCount != 0L, "downloadsCount should be deserialized")
        assertTrue(response.domain.isNotEmpty(), "domain should be deserialized")
        assertTrue(response.filesViews != 0L, "filesViews should be deserialized")
        assertTrue(response.authors != 0L, "authors should be deserialized")
        assertTrue(response.fileEndorsements != 0L, "fileEndorsements should be deserialized")
        assertTrue(response.modsCount != 0L, "modsCount should be deserialized")
        assertTrue(response.categories.isNotEmpty(), "categories should be deserialized")

        assertTrue(response.id == 3333L)
        assertTrue(response.name == "Cyberpunk 2077")
        assertTrue(response.nexusmodsUrl == "https://nexusmods.com/cyberpunk2077")
        assertTrue(response.domain == "cyberpunk2077")
    }

    @Test
    fun gameInfoModelListSerialization_test() {
        val response: List<GameInfoModel> = GetGames.response

        assertTrue(response.isNotEmpty(), "List should not be empty")

        response.forEach { game ->
            assertTrue(game.id != 0L, "id should be deserialized")
            assertTrue(game.name.isNotEmpty(), "name should be deserialized")
            assertTrue(game.forumUrl.isNotEmpty(), "forumUrl should be deserialized")
            assertTrue(game.nexusmodsUrl.isNotEmpty(), "nexusmodsUrl should be deserialized")
            assertTrue(game.genre.isNotEmpty(), "genre should be deserialized")
            assertTrue(game.filesCount != 0L, "filesCount should be deserialized")
            assertTrue(game.downloadsCount != 0L, "downloadsCount should be deserialized")
            assertTrue(game.domain.isNotEmpty(), "domain should be deserialized")
            assertTrue(game.filesViews != 0L, "filesViews should be deserialized")
            assertTrue(game.authors != 0L, "authors should be deserialized")
            assertTrue(game.fileEndorsements != 0L, "fileEndorsements should be deserialized")
            assertTrue(game.modsCount != 0L, "modsCount should be deserialized")
            assertTrue(game.categories.isNotEmpty(), "categories should be deserialized")
        }
    }
}
