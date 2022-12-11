package com.sedsoftware.nxmods.component.auth

import com.arkivanov.decompose.value.Value

interface NxModsAuth {

    val models: Value<Model>

    fun onTextEntered(text: String)

    fun onValidateButtonClicked()

    fun onNextButtonClicked()

    data class Model(
        val currentInput: String,
        val progressVisible: Boolean,
        val validateButtonAvailable: Boolean,
        val nextButtonAvailable: Boolean
    )

    sealed class Output {
        object NavigateToGameSelectionScreen : Output()
        object NavigateToHomeScreen : Output()
        data class ErrorCaught(val throwable: Throwable): Output()
    }
}
