package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.network.mappers.ModInfoModelMapper.modInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.ModInfoModelMapper.modInfoToDomain
import com.sedsoftware.nxmods.network.models.ModInfoModel
import com.sedsoftware.nxmods.network.stubs.responses.GetMod
import com.sedsoftware.nxmods.network.stubs.responses.GetModsList
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ModInfoModelMapperTest {

    @Test
    fun modInfoMapping_test() {
        val response: ModInfoModel = GetMod.response
        val mapped: ModInfo = modInfoToDomain(response)

        assertTrue(mapped.modId == 2380L, "modId should be mapped")
        assertTrue(mapped.gameId == 3333L, "gameId should be mapped")
        assertTrue(mapped.domainName == "cyberpunk2077", "domainName should be mapped")
        assertTrue(mapped.name == "RED4ext", "name should be mapped")
        assertTrue(mapped.version == "1.9.0", "version should be mapped")
        assertTrue(mapped.summary.isNotEmpty(), "summary should be mapped")
        assertTrue(mapped.description.isNotEmpty(), "description should be mapped")
        assertTrue(mapped.pictureUrl.isNotEmpty(), "pictureUrl should be mapped")
        assertTrue(mapped.modDownloads == 892560L, "modDownloads should be mapped")
        assertTrue(mapped.modDownloadsUnique == 519748L, "modDownloadsUnique should be mapped")
        assertTrue(mapped.uid == 14315125999948L, "uid should be mapped")
        assertTrue(mapped.categoryId == 12L, "categoryId should be mapped")
        assertTrue(mapped.endorsementCount == 13016L, "endorsementCount should be mapped")
        assertTrue(mapped.createdTimestamp == 1618832883L, "createdTimestamp should be mapped")
        assertTrue(mapped.updatedTimestamp == 1667929438L, "updatedTimestamp should be mapped")

        with(mapped.createdTime) {
            assertTrue(year == 2021, "year should be 2021")
            assertTrue(monthNumber == 4, "month should be 4")
            assertTrue(dayOfMonth == 19, "day should be 19")
        }

        with(mapped.updatedTime) {
            assertTrue(year == 2022, "year should be 2022")
            assertTrue(monthNumber == 11, "month should be 11")
            assertTrue(dayOfMonth == 8, "day should be 8")
        }

        assertTrue(mapped.author == "WopsS", "author should be mapped")
        assertTrue(mapped.uploadedBy == "WopsS", "uploadedBy should be mapped")
        assertTrue(mapped.uploaderProfileUrl.isNotEmpty(), "uploaderProfileUrl should be mapped")
        assertTrue(mapped.status.isNotEmpty(), "status should be mapped")
        assertTrue(mapped.available, "available should be mapped")
        assertFalse(mapped.isTracked, "not tracked by default")
        assertTrue(mapped.isEndorsed, "should be endorsed")
        assertTrue(mapped.user.name == "WopsS")
        assertTrue(mapped.user.memberId == 16475274L)
    }

    @Test
    fun modInfoMappingList_test() {
        val response: List<ModInfoModel> = GetModsList.response
        val mapped: List<ModInfo> = modInfoListToDomain(response)

        assertTrue(mapped.isNotEmpty(), "mapped list should not be empty")

        mapped.forEach { item ->
            assertTrue(item.modId != 0L, "modId should be mapped")
            assertTrue(item.gameId != 0L, "gameId should be mapped")
            assertTrue(item.domainName.isNotEmpty(), "domainName should be mapped")
            assertTrue(item.name.isNotEmpty(), "name should be mapped")
            assertTrue(item.version.isNotEmpty(), "version should be mapped")
            assertTrue(item.summary.isNotEmpty(), "summary should be mapped")
            assertTrue(item.description.isNotEmpty(), "description should be mapped")
            assertTrue(item.pictureUrl.isNotEmpty(), "pictureUrl should be mapped")
            assertTrue(item.modDownloads != 0L, "modDownloads should be mapped")
            assertTrue(item.modDownloadsUnique != 0L, "modDownloadsUnique should be mapped")
            assertTrue(item.uid != 0L, "uid should be mapped")
            assertTrue(item.categoryId != 0L, "categoryId should be mapped")
            assertTrue(item.endorsementCount != 0L, "endorsementCount should be mapped")
            assertTrue(item.createdTimestamp != 0L, "createdTimestamp should be mapped")
            assertTrue(item.updatedTimestamp != 0L, "updatedTimestamp should be mapped")

            with(item.createdTime) {
                assertTrue(year != 1970, "year should be mapped")
                assertTrue(monthNumber != 0, "month should be mapped")
                assertTrue(dayOfMonth != 0, "day should be mapped")
            }

            with(item.updatedTime) {
                assertTrue(year != 1970, "year should be mapped")
                assertTrue(monthNumber != 0, "month should be mapped")
                assertTrue(dayOfMonth != 0, "day should be mapped")
            }

            assertTrue(item.author.isNotEmpty(), "author should be mapped")
            assertTrue(item.uploadedBy.isNotEmpty(), "uploadedBy should be mapped")
            assertTrue(item.uploaderProfileUrl.isNotEmpty(), "uploaderProfileUrl should be mapped")
            assertTrue(item.status.isNotEmpty(), "status should be mapped")
            assertTrue(item.available, "available should be mapped")
            assertFalse(item.isTracked, "not tracked by default")
            assertTrue(item.user.name.isNotEmpty())
            assertTrue(item.user.memberId != 0L)
        }
    }
}
