package com.sedsoftware.nxmods.network.stubs

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal object Utils {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }

    inline fun <reified T> decodeFromJson(str: String): T {
        return json.decodeFromString(str)
    }
}
