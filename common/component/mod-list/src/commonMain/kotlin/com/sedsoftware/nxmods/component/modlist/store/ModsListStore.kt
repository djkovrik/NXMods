package com.sedsoftware.nxmods.component.modlist.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.Intent
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.Label
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.State
import com.sedsoftware.nxmods.domain.entity.ModInfo

internal interface ModsListStore : Store<Intent, State, Label> {

    sealed class Intent

    data class State(
        val mods: List<ModInfo> = emptyList(),
        val progressVisible: Boolean = true,
    )

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }
}
