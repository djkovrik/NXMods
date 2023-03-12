package com.sedsoftware.nxmods.component.modinfo

import com.arkivanov.decompose.value.Value

interface NxModInfo {

    val models: Value<Model>

    fun onEndorseButtonClicked()

    fun onUnendorseButtonClicked()

    fun onTrackButtonClicked()

    fun onUntrackButtonClicked()

    data class Model(
        val name: String,
        val summary: String,
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
    }
}
