package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.Utils
import kotlin.test.Test
import kotlin.test.assertTrue

class UserInfoModelTest {

    @Test
    fun userInfoModelSerialization_test() {
        val json = """
        {
            "member_id": 16475274,
            "member_group_id": 3,
            "name": "WopsS"
        }
        """.trimIndent()

        val deserialized: UserInfoModel = Utils.decodeFromJson(json)

        assertTrue(deserialized.name.isNotEmpty(), "Name should be deserialized")
        assertTrue(deserialized.memberId != 0L, "memberId should be deserialized")
    }
}
