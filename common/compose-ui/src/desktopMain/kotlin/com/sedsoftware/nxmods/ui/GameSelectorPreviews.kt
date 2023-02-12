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
            game = GameSelectorStates.loaded.games[1]
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
fun PreviewGamesLoaded() {
    NxModsTheme {
        NxModsGameSelectorScreen(
            model = GameSelectorStates.loaded
        )
    }
}
