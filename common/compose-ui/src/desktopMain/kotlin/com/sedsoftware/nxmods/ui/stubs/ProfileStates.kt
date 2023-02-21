package com.sedsoftware.nxmods.ui.stubs

import com.sedsoftware.nxmods.component.home.model.CurrentUser

internal object ProfileStates {

    val normal = CurrentUser(
        name = "DJ_Kovrik",
        avatar = "https://forums.nexusmods.com/uploads/profile/photo-100354.jpg",
        isPremium = false,
        isSupporter = false
    )

    val premium = CurrentUser(
        name = "DJ_Kovrik",
        avatar = "https://forums.nexusmods.com/uploads/profile/photo-100354.jpg",
        isPremium = true,
        isSupporter = false
    )

    val supporter = CurrentUser(
        name = "DJ_Kovrik",
        avatar = "https://forums.nexusmods.com/uploads/profile/photo-100354.jpg",
        isPremium = false,
        isSupporter = true
    )
}
