package com.sedsoftware.nxmods.component.gameselector

import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.component.gameselector.model.GameInfoModel

interface NxModsGameSelector {

    val models: Value<Model>

    fun onBookmarkClicked(domain: String)

    fun onNextButtonClicked()

    data class Model(
        val games: List<GameInfoModel>,
        val bookmarkedCounter: Int,
        val searchQuery: String,
        val searchVisible: Boolean,
        val progressVisible: Boolean,
        val nextButtonAvailable: Boolean,
        val emptyPlaceholderVisible: Boolean
    )

    sealed class Output {
        object NavigateToHomeScreen : Output()
        data class ErrorCaught(val throwable: Throwable) : Output()
    }
}
