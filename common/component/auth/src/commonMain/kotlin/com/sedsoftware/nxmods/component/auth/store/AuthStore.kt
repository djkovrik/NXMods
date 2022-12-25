package com.sedsoftware.nxmods.component.auth.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Intent
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Label
import com.sedsoftware.nxmods.component.auth.store.AuthStore.State

internal interface AuthStore: Store<Intent, State, Label> {

    sealed class Intent {
        class InputText(val text: String) : Intent()
        object ClickValidateButton : Intent()
        object ClickNextButton : Intent()
    }

    data class State(
        val textInput: String = "",
        val progressVisible: Boolean = true,
        val apiKeyStatus: ApiKeyStatus = ApiKeyStatus.NOT_CHECKED
    )

    sealed class Label {
        object ExistingUserValidationCompleted : Label()
        object NewUserValidationCompleted : Label()
        data class ErrorCaught(val throwable: Throwable): Label()
    }
}
