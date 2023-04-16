package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.sedsoftware.nxmods.ui.component.toolbar.base.ToolbarState
import com.sedsoftware.nxmods.ui.component.toolbar.scrollflags.BaseScrollState
import com.sedsoftware.nxmods.utils.asThumbnail

@Composable
internal fun NxModsInfoContent(component: NxModsInfo) {
    val model: NxModsInfo.Model by component.models.subscribeAsState()
    NxModsInfoScreen(
        model = model,
        onBackClicked = component::onBackClicked,
        onEndorseClicked = component::onEndorseClicked,
        onTrackClicked = component::onTrackClicked,
    )
}

@Composable
private fun rememberToolbarState(toolbarHeightRange: IntRange): ToolbarState {
    return rememberSaveable(saver = BaseScrollState.Saver) {
        BaseScrollState(toolbarHeightRange)
    }
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

    val currentAlpha = remember { toolbarState.progress }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondary.copy(
                    alpha = currentAlpha
                )
            )
    ) {
        // Content
        ModInfoContent(
            scrollState = scrollState,
            modifier = modifier,
            contentPadding = PaddingValues(top = MaxToolbarHeight)
        )

        // AppBar
        NxAppBarCollapsing(
            title = model.name,
            imageUrl = model.picture.asThumbnail(),
            progress = toolbarState.progress,
            currentAlpha = currentAlpha,
            modifier = Modifier
                .fillMaxWidth()
                .height(with(LocalDensity.current) { toolbarState.height.toDp() })
                .graphicsLayer { translationY = toolbarState.offset }
        )
    }
}

@Composable
private fun ModInfoContent(
    scrollState: ScrollState,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {

    ShapedSurface(
        paddingValues = contentPadding,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            repeat(50) {
                Text(
                    text = "Item $it",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier.padding(all = 16.dp)
                )
            }
        }
    }
}
