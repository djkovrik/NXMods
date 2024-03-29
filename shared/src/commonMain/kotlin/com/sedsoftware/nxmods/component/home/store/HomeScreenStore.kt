package com.sedsoftware.nxmods.component.home.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.home.model.CurrentUser
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Intent
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Label
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface HomeScreenStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class SelectGame(val name: String, val domain: String) : Intent()
        data class ShowNavDrawer(val visible: Boolean) : Intent()
    }

    data class State(
        val user: CurrentUser? = null,
        val currentGame: String = "",
        val currentDomain: String = "",
        val availableGames: List<GameInfo> = emptyList(),
        val navDrawerVisible: Boolean = false
    )

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
        object GameSwitched : Label()
    }
}
