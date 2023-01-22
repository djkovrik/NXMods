package com.sedsoftware.nxmods.component.gameselector.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.Intent
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.Label
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal interface GameSelectorStore : Store<Intent, State, Label> {

    sealed class Intent {
        data class BookmarkGame(val domain: String) : Intent()
        object ClickNextButton : Intent()
    }

    data class State(
        val games: List<GameInfo> = emptyList(),
        val progressVisible: Boolean = true,
        val bookmarkedGamesCounter: Int = 0,
        val nextButtonAvailable: Boolean = false
    )

    sealed class Label {
        object NextScreenRequested : Label()
        data class ErrorCaught(val throwable: Throwable) : Label()
    }
}
