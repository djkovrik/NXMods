package com.sedsoftware.nxmods.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector

@Composable
fun NxModsGameSelectorContent(component: NxModsGameSelector) {
    val model: NxModsGameSelector.Model by component.models.subscribeAsState()
    NxModsGameSelectorScreen(model)
}

@Composable
fun NxModsGameSelectorScreen(model: NxModsGameSelector.Model) {
    Text(text = "Work In Progress")
}
