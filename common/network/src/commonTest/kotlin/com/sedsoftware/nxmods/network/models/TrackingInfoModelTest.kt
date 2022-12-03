package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.responses.GetTracked
import kotlin.test.Test
import kotlin.test.assertTrue

class TrackingInfoModelTest {

    @Test
    fun trackingInfoModelSerialization_test() {
        val response: List<TrackingInfoModel> = GetTracked.response

        assertTrue(response.isNotEmpty(), "Response list should not be empty")

        response.forEach { item ->
            assertTrue(item.domain.isNotEmpty(), "domain should be deserialized")
            assertTrue(item.modId != 0L, "modId should be deserialized")
        }
    }
}
