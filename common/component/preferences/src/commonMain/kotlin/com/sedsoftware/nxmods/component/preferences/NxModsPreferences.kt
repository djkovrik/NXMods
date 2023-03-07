package com.sedsoftware.nxmods.component.preferences

import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferences

interface NxModsPreferences {

    val models: Value<Model>

//    fun <T> onPreferenceClicked(type: NxPreferenceKeyUnique)
//
//    fun <T> onPreferenceChanged(type: NxPreferenceKeyUnique, value: T)

    data class Model(
        val preferences: NxPreferences
    )

    sealed class Output {
        object GamesSelectorRequested : Output()
        object ScreenClosed : Output()
        data class ErrorCaught(val throwable: Throwable) : Output()
    }
}
