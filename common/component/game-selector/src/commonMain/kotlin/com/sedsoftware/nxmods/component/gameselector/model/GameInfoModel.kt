package com.sedsoftware.nxmods.component.gameselector.model

data class GameInfoModel(
    val id: Long,
    val name: String,
    val genre: String,
    val mods: String,
    val downloads: String,
    val domain: String,
    val bookmarked: Boolean,
)
