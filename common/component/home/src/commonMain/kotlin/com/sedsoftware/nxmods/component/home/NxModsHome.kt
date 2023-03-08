package com.sedsoftware.nxmods.component.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.component.home.model.CurrentUser
import com.sedsoftware.nxmods.component.home.model.NavDrawerGame
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.domain.type.ModListType

interface NxModsHome {

    val models: Value<Model>

    val childStack: Value<ChildStack<*, Child>>

    fun onLatestAddedTabClicked()
    fun onLatestUpdatedTabClicked()
    fun onTrendingTabClicked()
    fun onDrawerGameClicked(game: NavDrawerGame)
    fun onPreferenceIconClicked()
    fun onPreferencesChanged()

    data class Model(
        val user: CurrentUser?,
        val currentGame: String,
        val currentDomain: String,
        val games: List<NavDrawerGame>
    )

    sealed class Child(val type: ModListType, val index: Int) {
        class LatestAdded(val component: NxModsList) : Child(ModListType.LATEST_ADDED, 0)
        class LatestUpdated(val component: NxModsList) : Child(ModListType.LATEST_UPDATED, 1)
        class Trending(val component: NxModsList) : Child(ModListType.TRENDING, 2)
    }

    sealed class Output {
        data class ErrorCaught(val throwable: Throwable) : Output()
        object PreferenceScreenRequested : Output()
    }
}
