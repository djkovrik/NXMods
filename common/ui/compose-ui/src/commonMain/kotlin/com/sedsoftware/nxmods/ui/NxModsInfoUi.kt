package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.modinfo.NxModsInfo
import com.sedsoftware.nxmods.ui.component.toolbar.ToolbarState
import com.sedsoftware.nxmods.ui.component.toolbar.scrollflags.BaseScrollState

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
internal fun NxModsInfoScreen(
    model: NxModsInfo.Model,
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    onEndorseClicked: () -> Unit = {},
    onTrackClicked: () -> Unit = {},
) {


}

@Composable
private fun ModInfoContent(
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {

}
