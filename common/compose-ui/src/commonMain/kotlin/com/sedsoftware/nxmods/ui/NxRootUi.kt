@file:OptIn(ExperimentalDecomposeApi::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.sedsoftware.nxmods.root.NxModsRoot
import kotlinx.coroutines.launch

@Composable
fun NxModsRootContent(component: NxModsRoot) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {

        DisposableScope(component) {
            component.messages.subscribeScoped { message ->
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = message
                    )
                }
            }
        }

        Children(
            stack = component.childStack,
            animation = stackAnimation(fade() + scale()),
        ) {
            when (val child = it.instance) {
                is NxModsRoot.Child.Auth -> NxModsAuthContent(child.component)
                is NxModsRoot.Child.GameSelector -> NxModsGameSelectorContent(child.component)
                is NxModsRoot.Child.ModsList -> NxModsListContent(child.component)
            }
        }
    }
}
