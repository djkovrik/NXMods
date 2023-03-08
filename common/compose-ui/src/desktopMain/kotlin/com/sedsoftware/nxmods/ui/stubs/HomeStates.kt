@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui.stubs

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import com.sedsoftware.nxmods.component.home.model.NavDrawerGame
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.component.modlist.model.ModInfoModel

internal object HomeStates {

    val drawer = DrawerState(DrawerValue.Closed)

    val mod = ModInfoModel(
        modId = 123L,
        gameId = 123L,
        name = "This is some mod name",
        summary = "Mod which does some amazing things Mod which does some amazing things Mod which does some amazing things " +
            "Mod which does some amazing things Mod which does some amazing things",
        picture = "https://staticdelivery.nexusmods.com/mods/3333/images/5646/5646-1664963894-1336192582.png",
        author = "DJ_Kovrik",
        domain = "cyberpunk2077",
        category = "Gameplay",
        downloads = "1.2k",
        endorsements = "2.3k"
    )

    val modList = NxModsList.Model(
        progressVisible = false,
        mods = listOf(
            mod,
            mod.copy(name = "Mod name 2"),
            mod.copy(name = "Mod name 3"),
            mod.copy(name = "Mod name 4"),
            mod.copy(name = "Mod name 5"),
        ),
        emptyListPlaceholderVisible = false
    )

    val drawerGame = NavDrawerGame(
        name = "Cyberpunk 2077",
        domain = "cyberpunk2077",
        selected = false
    )

    val drawerGameSelected = drawerGame.copy(
        selected = true
    )
}
