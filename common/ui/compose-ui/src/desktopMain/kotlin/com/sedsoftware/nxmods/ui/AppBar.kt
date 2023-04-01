package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.ui.component.NxAppBarCollapsing
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Preview
@Composable
fun CollapsingToolbarCollapsedPreview() {
    NxModsTheme {
        NxAppBarCollapsing(
            imageUrl = "",
            progress = 0f,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarHalfwayPreview() {
    NxModsTheme {
        NxAppBarCollapsing(
            imageUrl = "",
            progress = 0.5f,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
    }
}

@Preview
@Composable
fun CollapsingToolbarExpandedPreview() {
    NxModsTheme {
        NxAppBarCollapsing(
            imageUrl = "",
            progress = 1f,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}
