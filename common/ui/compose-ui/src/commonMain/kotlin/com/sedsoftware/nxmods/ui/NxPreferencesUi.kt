@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.preferences.NxModsPreferences
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceButton
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceItem
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceSwitch
import com.sedsoftware.nxmods.ui.component.preference.PreferenceButton
import com.sedsoftware.nxmods.ui.component.preference.PreferenceCategory
import com.sedsoftware.nxmods.ui.component.preference.PreferenceSwitcher
import dev.icerock.moko.resources.compose.stringResource

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

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(MR.strings.preferences_title),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = modifier.padding(vertical = 16.dp, horizontal = 32.dp)
                    )
                },
                actions = {
                    IconButton(
                        onClick = onCloseClicked,
                        modifier = modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = modifier,
            )
        }
    ) { paddingValues ->

        Column(modifier = modifier.padding(paddingValues)) {
            model.preferences.groups.forEach { group ->
                PreferenceCategory(
                    category = group.category,
                    modifier = modifier
                )

                LazyColumn(modifier = modifier) {
                    items(group.items) { item: NxPreferenceItem ->
                        when (item) {
                            is NxPreferenceButton -> {
                                PreferenceButton(
                                    key = item.key,
                                    modifier = modifier,
                                    onClick = onButtonClicked
                                )
                            }

                            is NxPreferenceSwitch -> {
                                PreferenceSwitcher(
                                    key = item.key,
                                    value = item.value,
                                    modifier = modifier,
                                    onSwitchChange = onSwitchChanged
                                )
                            }
                        }
                    }
                }

                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            }
        }

    }
}
