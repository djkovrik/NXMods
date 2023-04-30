package com.sedsoftware.nxmods.component.modinfo.store

import com.arkivanov.mvikotlin.core.store.Store
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.Intent
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.Label
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.State
import kotlinx.datetime.LocalDateTime

internal interface ModInfoStore : Store<Intent, State, Label> {

    sealed class Intent {
        object Endorse : Intent()
        object Track : Intent()
    }

    data class State(
        val id: Long = -1L,
        val domain: String = "",
        val name: String = "",
        val summary: String = "",
        val description: String = "",
        val version: String = "",
        val pictureUrl: String = "",
        val categoryName: String = "",
        val downloads: Long = -1L,
        val endorsements: Long = -1L,
        val createdTime: LocalDateTime? = null,
        val updatedTime: LocalDateTime? = null,
        val author: String = "",
        val isTracked: Boolean = false,
        val isEndorsed: Boolean = false,
        val loadingInProgress: Boolean = true,
    )

    sealed class Label {
        data class ErrorCaught(val throwable: Throwable) : Label()
    }
}
