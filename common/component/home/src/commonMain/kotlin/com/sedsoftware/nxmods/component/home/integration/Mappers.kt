package com.sedsoftware.nxmods.component.home.integration

import com.sedsoftware.nxmods.component.home.NxModsHome.Model
import com.sedsoftware.nxmods.component.home.model.NavDrawerGame
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.State

internal val stateToModel: (State) -> Model = { state ->
    Model(
        user = state.user,
        currentGame = state.currentGame,
        currentDomain = state.currentDomain,
        navDrawerVisible = state.navDrawerVisible,
        games = state.availableGames.map { item ->
            NavDrawerGame(
                name = item.name,
                domain = item.domain,
                selected = state.currentDomain == item.domain,
            )
        }
    )
}
