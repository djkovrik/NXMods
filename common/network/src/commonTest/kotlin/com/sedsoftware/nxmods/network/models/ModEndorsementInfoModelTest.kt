package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.Utils
import kotlin.test.Test
import kotlin.test.assertTrue

class ModEndorsementInfoModelTest {

    @Test
    fun modEndorsementInfoSerialization_test() {
        val json = """
        {
            "endorse_status": "Endorsed",
            "timestamp": 1618930341,
            "version": null
        }
        """.trimIndent()

        val deserialized: ModEndorsementInfoModel = Utils.decodeFromJson(json)

        assertTrue(deserialized.status.isNotEmpty())
    }
}
