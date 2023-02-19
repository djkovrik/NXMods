package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.stubs.GameSelectorStates
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Preview
@Composable
fun PreviewGameItem() {
    NxModsTheme {
        NxModsGameItem(
            model = GameSelectorStates.loaded.games[0]
        )
    }
}

@Preview
@Composable
fun PreviewGameItemDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsGameItem(
            model = GameSelectorStates.loaded.games[0]
        )
    }
}

@Preview
@Composable
fun PreviewGamesLoading() {
    NxModsTheme {
        NxModsGameSelectorScreen(
            model = GameSelectorStates.loading
        )
    }
}

@Preview
@Composable
fun PreviewGamesLoadingDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsGameSelectorScreen(
            model = GameSelectorStates.loading
        )
    }
}

@Preview
@Composable
fun PreviewGamesLoaded() {
    NxModsTheme {
        NxModsGameSelectorScreen(
            model = GameSelectorStates.loaded
        )
    }
}

@Preview
@Composable
fun PreviewGamesLoadedDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsGameSelectorScreen(
            model = GameSelectorStates.loaded
        )
    }
}
