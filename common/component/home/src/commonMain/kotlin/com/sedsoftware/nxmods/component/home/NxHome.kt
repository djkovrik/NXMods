package com.sedsoftware.nxmods.component.home

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.domain.type.ModListType

interface NxHome {

    val models: Value<Model>

    val childStack: Value<ChildStack<*, Child>>

    fun onLatestAddedTabClicked()
    fun onLatestUpdatedTabClicked()
    fun onTrendingTabClicked()

    data class Model(
        val currentGame: String,
        val currentDomain: String,
    )

    sealed class Child(val type: ModListType) {
        class LatestAdded(val component: NxModsList) : Child(ModListType.LATEST_ADDED)
        class LatestUpdated(val component: NxModsList) : Child(ModListType.LATEST_UPDATED)
        class Trending(val component: NxModsList) : Child(ModListType.TRENDING)
    }

    sealed class Output {
        data class ErrorCaught(val throwable: Throwable) : Output()
    }
}
