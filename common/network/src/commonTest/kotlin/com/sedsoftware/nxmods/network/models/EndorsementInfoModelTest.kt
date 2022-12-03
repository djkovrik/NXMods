package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.responses.GetEndorsed
import kotlin.test.Test
import kotlin.test.assertTrue

class EndorsementInfoModelTest {

    @Test
    fun endorsementInfoSerialization_test() {
        val response: List<EndorsementInfoModel> = GetEndorsed.response

        assertTrue(response.isNotEmpty(), "List should not be empty")
        assertTrue(response.count { it.domain == "skyrim" } > 0, "Should have skyrim items")

        response.forEach { item: EndorsementInfoModel ->
            assertTrue(item.modId != 0L, "Mod id should be defined")
            assertTrue(item.domain.isNotEmpty(), "Domain should be defined")
        }
    }
}
