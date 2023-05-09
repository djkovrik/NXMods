package com.sedsoftware.nxmods.ui.stubs

import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus

internal object AuthStates {

    private const val API_KEY_STUB = "XBuYsJB4YRTwJTo2PvN8TExtucAzw03F5JJSjyd4NWNL--ytySJTmNkAYzxmcp--UdEI2QoHMDEU4+J8mZ9YAt=="

    private val default = NxModsAuth.Model(
        currentInput = "",
        progressVisible = false,
        validateButtonAvailable = false,
        nextButtonAvailable = false,
        status = ApiKeyStatus.NOT_CHECKED
    )

    val loading = default.copy(progressVisible = true)

    val newUser = default.copy(status = ApiKeyStatus.NOT_FOUND)

    val newUserKeyEntered = default.copy(
        currentInput = API_KEY_STUB,
        validateButtonAvailable = true,
        status = ApiKeyStatus.NOT_FOUND
    )

    val newUserKeyValidating = newUserKeyEntered.copy(
        progressVisible = false,
        validateButtonAvailable = false,
        status = ApiKeyStatus.VALIDATION
    )

    val newUserKeyValid = newUserKeyEntered.copy(
        status = ApiKeyStatus.VALID,
        validateButtonAvailable = false,
        nextButtonAvailable = true
    )

    val newUserKeyInvalid = newUserKeyEntered.copy(
        status = ApiKeyStatus.INVALID
    )
}
