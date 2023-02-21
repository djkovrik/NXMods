package com.sedsoftware.nxmods.component.home.model

data class CurrentUser(
    val name: String,
    val avatar: String,
    val isPremium: Boolean,
    val isSupporter: Boolean
)
