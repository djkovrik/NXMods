package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.stubs.GameSelectorStates

@Preview
@Composable
fun PreviewGameItem() {
    NxModsGameItem(
        game = GameSelectorStates.loaded.games[1]
    )
}


@Preview
@Composable
fun PreviewGamesLoading() {
    NxModsGameSelectorScreen(
        model = GameSelectorStates.loading
    )
}

@Preview
@Composable
fun PreviewGamesLoaded() {
    NxModsGameSelectorScreen(
        model = GameSelectorStates.loaded
    )
}
