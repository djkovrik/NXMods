package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.modinfo.NxModsInfo
import com.sedsoftware.nxmods.ui.component.NxAppBarCollapsing
import com.sedsoftware.nxmods.ui.component.ShapedSurface
import com.sedsoftware.nxmods.ui.component.toolbar.MaxToolbarHeight
import com.sedsoftware.nxmods.ui.component.toolbar.MinToolbarHeight
import com.sedsoftware.nxmods.ui.component.toolbar.ToolbarState
import com.sedsoftware.nxmods.ui.component.toolbar.scrollflags.ScrollState

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = ScrollState.Saver) {
        ScrollState(toolbarHeightRange)
    }
}

@Composable
internal fun NxModsInfoContent(component: NxModsInfo) {
    val model: NxModsInfo.Model by component.models.subscribeAsState()
    NxModsInfoScreen(
        model = model,
        onEndorseClicked = component::onEndorseClicked,
        onTrackClicked = component::onTrackClicked,
    )
}

@Composable
internal fun NxModsInfoScreen(
    model: NxModsInfo.Model,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    onEndorseClicked: () -> Unit = {},
    onTrackClicked: () -> Unit = {},
) {

    val toolbarHeightRange = with(LocalDensity.current) {
        MinToolbarHeight.roundToPx()..MaxToolbarHeight.roundToPx()
    }

    val toolbarState = rememberToolbarState(toolbarHeightRange)
    val scrollState = rememberScrollState()

    toolbarState.scrollValue = scrollState.value

    Box(modifier = modifier) {
        ShapedSurface(paddingValues = PaddingValues(top = MaxToolbarHeight)) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                PageContent(
                    model = model,
                    modifier = modifier
                )
            }
        }

        NxAppBarCollapsing(
            imageUrl = model.picture,
            progress = toolbarState.progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                .graphicsLayer { translationY = toolbarState.offset }
        )
    }
}

@Composable
private fun PageContent(
    model: NxModsInfo.Model,
    modifier: Modifier = Modifier,
) {
    for (i in 1..50) {
        Text(
            text = "Item $i",
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = modifier.height(8.dp))
    }
}
