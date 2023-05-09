package com.sedsoftware.nxmods.component.home.domain

internal interface NxModsGameSwitcherSettings {
    var selectedGameName: String
    var selectedGameDomain: String
    val userName: String
    val userAvatar: String
    val isPremium: Boolean
    val isSupporter: Boolean
}
