package com.sedsoftware.nxmods.component.home.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Intent
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Label
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface HomeScreenStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class SelectGame(val name: String, val domain: String) : Intent()
    }

    data class State(
        val currentGame: String = "",
        val currentDomain: String = "",
        val availableGames: List<GameInfo> = emptyList()
    )

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }
}
