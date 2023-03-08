package com.sedsoftware.nxmods.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.preferences.NxModsPreferences
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique

@Composable
fun NxModsPreferencesContent(component: NxModsPreferences) {
    val model: NxModsPreferences.Model by component.models.subscribeAsState()
    NxModsPreferencesScreen(
        model = model,
        onButtonClicked = component::onButtonClicked,
        onSwitchChanged = component::onSwitchChanged,
        onCloseClicked = component::onCloseButtonClicked
    )
}

@Composable
internal fun NxModsPreferencesScreen(
    model: NxModsPreferences.Model,
    modifier: Modifier = Modifier,
    onButtonClicked: (NxPreferenceKeyUnique) -> Unit = {},
    onSwitchChanged: (NxPreferenceKeyUnique, Boolean) -> Unit = { _, _ -> },
    onCloseClicked: () -> Unit = {}
) {

    Text(text = "TODO")
}
