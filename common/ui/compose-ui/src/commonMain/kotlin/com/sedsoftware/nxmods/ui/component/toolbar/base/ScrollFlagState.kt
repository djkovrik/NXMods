package com.sedsoftware.nxmods.ui.component.toolbar.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy

@Suppress("ObjectPropertyNaming")
abstract class ScrollFlagState(heightRange: IntRange, scrollValue: Int) : ToolbarState {

    init {
        require(heightRange.first >= 0 && heightRange.last >= heightRange.first) {
            "The lowest height value must be >= 0 and the highest height value must be >= the lowest value."
        }
    }

    protected val minHeight = heightRange.first
    protected val maxHeight = heightRange.last
    protected val rangeDifference = heightRange.last - heightRange.first

    @Suppress("VariableNaming")
    protected var _scrollValue by mutableStateOf(
        value = scrollValue.coerceAtLeast(0),
        policy = structuralEqualityPolicy()
    )

    final override val progress: Float
        get() = 1 - (maxHeight - height) / rangeDifference
}
