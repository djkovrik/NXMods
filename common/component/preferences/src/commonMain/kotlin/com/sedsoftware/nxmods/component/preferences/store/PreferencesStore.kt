package com.sedsoftware.nxmods.component.preferences.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferences
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.Intent
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.Label
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.State

internal interface PreferencesStore : Store<Intent, State, Label> {

    sealed class Intent {
        class ClickButton(val key: NxPreferenceKeyUnique) : Intent()
        class ChangeSwitch(val key: NxPreferenceKeyUnique, val value: Boolean) : Intent()
        object CloseScreen : Intent()
    }

    data class State(
        val preferences: NxPreferences = NxPreferences()
    )

    sealed class Label {
        object GameSelectorRequested : Label()
        object PreferencesChanged : Label()
        object ScreenClosed : Label()
        data class ErrorCaught(val throwable: Throwable) : Label()
    }
}
