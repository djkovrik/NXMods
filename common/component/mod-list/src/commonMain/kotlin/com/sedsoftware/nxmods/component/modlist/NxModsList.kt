package com.sedsoftware.nxmods.component.modlist

import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.component.modlist.model.ModInfoModel

interface NxModsList {

    val models: Value<Model>

    fun onModInfoClick(domain: String, id: Long)

    data class Model(
        val mods: List<ModInfoModel>,
        val progressVisible: Boolean,
        val emptyListPlaceholderVisible: Boolean
    )

    sealed class Output {
        data class OpenModInfo(val domain: String, val id: Long) : Output()
        data class ErrorCaught(val throwable: Throwable) : Output()
    }
}
