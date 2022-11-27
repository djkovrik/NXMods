package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.responses.ValidateApiKey
import kotlin.test.Test
import kotlin.test.assertTrue

class OwnProfileModelTest {

    @Test
    fun ownProfileModelSerialization_test() {
        val response: OwnProfileModel = ValidateApiKey.response

        assertTrue(response.userId != 0L, "userId should be deserialized")
        assertTrue(response.key.isNotEmpty(), "key should be deserialized")
        assertTrue(response.name.isNotEmpty(), "name should be deserialized")
        assertTrue(response.isPremium, "premium should be deserialized")
        assertTrue(response.isSupporter, "supporter should be deserialized")
        assertTrue(response.email.isNotEmpty(), "email should be deserialized")
        assertTrue(response.avatar.isNotEmpty(), "avatar should be deserialized")
    }
}
