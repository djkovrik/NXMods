package com.sedsoftware.nxmods.component.gameselector

import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.domain.entity.GameInfo

interface NxModsGameSelector {

    val models: Value<Model>

    fun onBookmarkClicked(domain: String)

    fun onNextButtonClicked()

    data class Model(
        val games: List<GameInfo>,
        val bookmarkedCounter: Int,
        val progressVisible: Boolean,
        val nextButtonAvailable: Boolean
    )

    sealed class Output {
        object NavigateToHomeScreen : Output()
        data class ErrorCaught(val throwable: Throwable) : Output()
    }
}
