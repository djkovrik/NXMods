package com.sedsoftware.nxmods.component.preferences

import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferences

interface NxModsPreferences {

    val models: Value<Model>

    fun onButtonClicked(key: NxPreferenceKeyUnique)

    fun onSwitchChanged(key: NxPreferenceKeyUnique, value: Boolean)

    data class Model(
        val preferences: NxPreferences
    )

    sealed class Output {
        data class ErrorCaught(val throwable: Throwable) : Output()
        object GamesSelectorRequested : Output()
        object PreferencesChanged : Output()
        object ScreenClosed : Output()
    }
}
