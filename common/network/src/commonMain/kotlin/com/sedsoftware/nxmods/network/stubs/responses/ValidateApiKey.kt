package com.sedsoftware.nxmods.network.stubs.responses

import com.sedsoftware.nxmods.network.models.OwnProfileModel
import com.sedsoftware.nxmods.network.stubs.Utils

internal object ValidateApiKey {

    val response: OwnProfileModel
        get() = Utils.decodeFromJson(json)

    private val json = """
        {
            "user_id": 100354,
            "key": "QcqkPUMxRUv717ge0pJNB8h0iA+43QcMaylKXw60X3ue--4W/d9AsDEjrpPvmQ--migB3fb62BgDa1/QNZL40g==",
            "name": "djkovrik",
            "is_premium?": true,
            "is_supporter?": true,
            "email": "djkovrik@gmail.com",
            "profile_url": "https://forums.nexusmods.com/uploads/profile/photo-100354.jpg",
            "is_supporter": true,
            "is_premium": true
        }
    """.trimIndent()
}
