package com.sedsoftware.nxmods.ui.component.toolbar.base

import androidx.compose.runtime.Stable

@Stable
interface ToolbarState {
    val offset: Float
    val height: Float
    val progress: Float
    var scrollValue: Int
}
