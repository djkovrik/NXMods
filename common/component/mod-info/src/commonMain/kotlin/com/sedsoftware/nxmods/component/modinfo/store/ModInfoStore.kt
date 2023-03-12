package com.sedsoftware.nxmods.component.modinfo.store

import kotlinx.datetime.LocalDateTime

internal interface ModInfoStore {

    sealed class Intent {
        object Endorse : Intent()
        object Unendorse : Intent()
        object Track : Intent()
        object Untrack : Intent()
    }

    data class State(
        val id: Long = -1L,
        val domain: String = "",
        val name: String = "",
        val summary: String = "",
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
