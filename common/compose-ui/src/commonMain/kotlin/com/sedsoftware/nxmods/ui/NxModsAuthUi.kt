package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.auth.NxModsAuth

@Composable
fun NxModsAuthContent(component: NxModsAuth) {
    val model: NxModsAuth.Model by component.models.subscribeAsState()
    NxModsAuthScreen(model)
}

@Composable
fun NxModsAuthScreen(model: NxModsAuth.Model) {
    Column {
        Text(text = "Hello world")
    }
}
