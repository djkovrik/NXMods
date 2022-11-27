package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.network.mappers.GameInfoModelMapper.gameInfoModelListToDomain
import com.sedsoftware.nxmods.network.mappers.GameInfoModelMapper.gameInfoModelToDomain
import com.sedsoftware.nxmods.network.models.GameInfoModel
import com.sedsoftware.nxmods.network.stubs.responses.GetGame
import com.sedsoftware.nxmods.network.stubs.responses.GetGames
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GameInfoModelMapperTest {

    @Test
    fun gameInfoMapping_test() {
        val response: GameInfoModel = GetGame.response
        val mapped: GameInfo = gameInfoModelToDomain(response)

        assertTrue(mapped.id != 0L, "id should be mapped")
        assertTrue(mapped.name.isNotEmpty(), "name should be mapped")
        assertTrue(mapped.forumUrl.isNotEmpty(), "forumUrl should be mapped")
        assertTrue(mapped.nexusmodsUrl.isNotEmpty(), "nexusmodsUrl should be mapped")
        assertTrue(mapped.genre.isNotEmpty(), "genre should be mapped")
        assertTrue(mapped.domain.isNotEmpty(), "domain should be mapped")
        assertTrue(mapped.filesCount != 0L, "filesCount should be mapped")
        assertTrue(mapped.downloadsCount != 0L, "downloadsCount should be mapped")
        assertTrue(mapped.filesViews != 0L, "filesViews should be mapped")
        assertTrue(mapped.authors != 0L, "authors should be mapped")
        assertTrue(mapped.fileEndorsements != 0L, "fileEndorsements should be mapped")
        assertTrue(mapped.modsCount != 0L, "modsCount should be mapped")
        assertFalse(mapped.isBookmarked, "not bookmarked by default")
        assertTrue(mapped.categories.isNotEmpty(), "categories should be mapped")

        assertTrue(response.id == 3333L)
        assertTrue(response.name == "Cyberpunk 2077")
        assertTrue(response.nexusmodsUrl == "https://nexusmods.com/cyberpunk2077")
        assertTrue(response.domain == "cyberpunk2077")
    }

    @Test
    fun gameInfoMappingList_test() {
        val response: List<GameInfoModel> = GetGames.response
        val mapped: List<GameInfo> = gameInfoModelListToDomain(response)

        assertTrue(mapped.isNotEmpty(), "mapped list should not be empty")

        mapped.forEach { item ->
            assertTrue(item.id != 0L, "id should be mapped")
            assertTrue(item.name.isNotEmpty(), "name should be mapped")
            assertTrue(item.forumUrl.isNotEmpty(), "forumUrl should be mapped")
            assertTrue(item.nexusmodsUrl.isNotEmpty(), "nexusmodsUrl should be mapped")
            assertTrue(item.genre.isNotEmpty(), "genre should be mapped")
            assertTrue(item.domain.isNotEmpty(), "domain should be mapped")
            assertTrue(item.filesCount != 0L, "filesCount should be mapped")
            assertTrue(item.downloadsCount != 0L, "downloadsCount should be mapped")
            assertTrue(item.filesViews != 0L, "filesViews should be mapped")
            assertTrue(item.authors != 0L, "authors should be mapped")
            assertTrue(item.fileEndorsements != 0L, "fileEndorsements should be mapped")
            assertTrue(item.modsCount != 0L, "modsCount should be mapped")
            assertFalse(item.isBookmarked, "not bookmarked by default")
            assertTrue(item.categories.isNotEmpty(), "categories should be mapped")
        }
    }
}
