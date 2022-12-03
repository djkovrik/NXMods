package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.responses.GetMod
import kotlin.test.Test
import kotlin.test.assertTrue

class ModInfoModelTest {

    @Test
    fun modInfoModelSerialization_test() {
        val response: ModInfoModel = GetMod.response

        assertTrue(response.modId != 0L, "modId should be deserialized")
        assertTrue(response.gameId != 0L, "gameId should be deserialized")
        assertTrue(response.domainName.isNotEmpty(), "domainName should be deserialized")
        assertTrue(response.name.isNotEmpty(), "name should be deserialized")
        assertTrue(response.summary.isNotEmpty(), "summary should be deserialized")
        assertTrue(response.version.isNotEmpty(), "version should be deserialized")
        assertTrue(response.description.isNotEmpty(), "description should be deserialized")
        assertTrue(response.pictureUrl.isNotEmpty(), "pictureUrl should be deserialized")
        assertTrue(response.modDownloads != 0L, "modDownloads should be deserialized")
        assertTrue(response.modDownloadsUnique != 0L, "modDownloadsUnique should be deserialized")
        assertTrue(response.uid != 0L, "uid should be deserialized")
        assertTrue(response.categoryId != 0L, "categoryId should be deserialized")
        assertTrue(response.endorsementCount != 0L, "endorsementCount should be deserialized")
        assertTrue(response.createdTimestamp != 0L, "createdTimestamp should be deserialized")
        assertTrue(response.updatedTimestamp != 0L, "updatedTimestamp should be deserialized")
        assertTrue(response.author.isNotEmpty(), "author should be deserialized")
        assertTrue(response.uploadedBy.isNotEmpty(), "uploadedBy should be deserialized")
        assertTrue(response.uploaderProfileUrl.isNotEmpty(), "uploaderProfileUrl should be deserialized")
        assertTrue(response.status.isNotEmpty(), "status should be deserialized")
        assertTrue(response.available, "available should be deserialized")
        assertTrue(response.user.name.isNotEmpty(), "user should be deserialized")
        assertTrue(response.endorsement != null, "endorsement should be deserialized")
    }
}
