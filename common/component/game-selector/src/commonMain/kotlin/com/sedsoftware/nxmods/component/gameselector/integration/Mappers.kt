package com.sedsoftware.nxmods.component.gameselector.integration

import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector.Model
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.State

internal val stateToModel: (State) -> Model = {
    Model(
        games = it.games,
        bookmarkedCounter = it.bookmarkedGamesCounter,
        progressVisible = it.progressVisible,
        nextButtonAvailable = it.bookmarkedGamesCounter > 0
    )
}
