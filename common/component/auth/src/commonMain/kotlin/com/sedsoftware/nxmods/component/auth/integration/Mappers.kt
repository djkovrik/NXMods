package com.sedsoftware.nxmods.component.auth.integration

import com.sedsoftware.nxmods.component.auth.NxModsAuth.Model
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.component.auth.store.AuthStore.State

internal val stateToModel: (State) -> Model = {
    Model(
        currentInput = it.textInput,
        progressVisible = it.progressVisible,
        validateButtonAvailable = it.textInput.isNotEmpty() && !it.progressVisible,
        nextButtonAvailable = it.apiKeyStatus == ApiKeyStatus.VALID
    )
}
