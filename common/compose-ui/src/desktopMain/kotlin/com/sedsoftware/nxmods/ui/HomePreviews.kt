package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.stubs.HomeStates
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Composable
@Preview
fun ModListItemPreview() {
    NxModsTheme {
        ModListItem(HomeStates.mod)
    }
}

@Composable
@Preview
fun ModListPreview() {
    NxModsTheme {
        NxModsListScreen(HomeStates.modList)
    }
}

@Composable
@Preview
fun ModListPreviewDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsListScreen(HomeStates.modList)
    }
}
