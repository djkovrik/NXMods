package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.modinfo.NxModsInfo
import com.sedsoftware.nxmods.markdown.NxMarkdown

@Composable
internal fun NxModsInfoContent(component: NxModsInfo) {
    val model: NxModsInfo.Model by component.models.subscribeAsState()
    NxModsInfoScreen(
        model = model,
        onEndorseClicked = component::onEndorseClicked,
        onUnendorseClicked = component::onUnendorseClicked,
        onTrackClicked = component::onTrackClicked,
        onUntrackClicked = component::onUntrackClicked
    )
}

@Composable
internal fun NxModsInfoScreen(
    model: NxModsInfo.Model,
    modifier: Modifier = Modifier,
    onEndorseClicked: () -> Unit = {},
    onUnendorseClicked: () -> Unit = {},
    onTrackClicked: () -> Unit = {},
    onUntrackClicked: () -> Unit = {},
) {

    if (model.progressVisible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = modifier
            )
        }
    } else {
        // TODO header + markdown
    }
}
