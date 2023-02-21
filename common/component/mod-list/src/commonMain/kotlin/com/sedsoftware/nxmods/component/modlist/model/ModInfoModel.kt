package com.sedsoftware.nxmods.component.modlist.model

data class ModInfoModel(
    val modId: Long,
    val gameId: Long,
    val picture: String,
    val domain: String,
    val name: String,
    val summary: String,
    val author: String,
    val category: String,
    val downloads: String,
    val endorsements: String,
)
