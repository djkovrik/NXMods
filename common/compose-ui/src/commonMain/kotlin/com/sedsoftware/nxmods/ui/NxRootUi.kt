@file:OptIn(ExperimentalDecomposeApi::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.sedsoftware.nxmods.root.NxModsRoot

@Composable
fun NxModsRootContent(component: NxModsRoot) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(fade() + scale()),
    ) {
        when (val child = it.instance) {
            is NxModsRoot.Child.Auth -> NxModsAuthContent(child.component)
        }
    }
}
