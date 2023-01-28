package com.sedsoftware.nxmods.component.modlist

import com.arkivanov.decompose.value.Value
import com.sedsoftware.nxmods.domain.entity.ModInfo

interface NxModsList {

    val models: Value<Model>

    fun onModInfoClick(domain: String, id: Long)

    data class Model(
        val mods: List<ModInfo>,
        val progressVisible: Boolean,
    )

    sealed class Output {
        data class OpenModInfo(val domain: String, val id: Long) : Output()
        data class ErrorCaught(val throwable: Throwable) : Output()
    }
}
