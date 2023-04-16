package com.sedsoftware.nxmods.component.modinfo

import com.arkivanov.decompose.value.Value

interface NxModsInfo {

    val models: Value<Model>

    fun onEndorseClicked()

    fun onTrackClicked()

    fun onBackClicked()

    data class Model(
        val name: String,
        val summary: String,
        val description: String,
        val version: String,
        val picture: String,
        val category: String,
        val downloads: String,
        val endorsements: String,
        val createdTime: String,
        val updatedTime: String,
        val author: String,
        val endorsed: Boolean,
        val tracked: Boolean,
        val progressVisible: Boolean,
    )

    sealed class Output {
        data class ErrorCaught(val throwable: Throwable) : Output()
        object ScreenCloseRequested : Output()
    }
}
