package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.network.mappers.OwnProfileMapper.profileToDomain
import com.sedsoftware.nxmods.network.models.OwnProfileModel
import com.sedsoftware.nxmods.network.stubs.responses.ValidateApiKey
import kotlin.test.Test
import kotlin.test.assertTrue

class OwnProfileMapperTest {

    @Test
    fun ownProfileMapping_test() {
        val response: OwnProfileModel = ValidateApiKey.response
        val mapped: OwnProfile = profileToDomain(response)

        assertTrue(mapped.userId == 100354L, "User id should be mapped")
        assertTrue(mapped.key == "thisissomeapikey", "api key id should be mapped")
        assertTrue(mapped.name == "djkovrik", "name should be mapped")
        assertTrue(mapped.email == "myemail@mail.com", "email id should be mapped")
        assertTrue(mapped.isPremium, "Premium should be mapped")
        assertTrue(mapped.isSupporter, "Supporter should be mapped")
        assertTrue(mapped.avatar.isNotEmpty(), "avatar should be mapped")
    }
}
