package com.sedsoftware.nxmods.component.home.integration

import com.sedsoftware.nxmods.component.home.NxModsHome.Model
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.State

internal val stateToModel: (State) -> Model = {
    Model(
        currentGame = it.currentGame,
        currentDomain = it.currentDomain,
    )
}
