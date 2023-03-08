@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.component.DrawerGameListItem
import com.sedsoftware.nxmods.ui.stubs.HomeStates
import com.sedsoftware.nxmods.ui.stubs.HomeStates.drawer
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Composable
@Preview
fun ModListItemPreview() {
    NxModsTheme {
        ModListItem(HomeStates.mod, 0)
    }
}

@Composable
@Preview
fun ModListPreview() {
    NxModsTheme {
        NxModsListScreen(HomeStates.modList)
    }
}

@Composable
@Preview
fun ModListPreviewDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsListScreen(HomeStates.modList)
    }
}

@Composable
@Preview
fun NavDrawerGame() {
    NxModsTheme {
        DrawerGameListItem(HomeStates.drawerGame, drawer)
    }
}

@Composable
@Preview
fun NavDrawerGameDark() {
    NxModsTheme(useDarkTheme = true) {
        DrawerGameListItem(HomeStates.drawerGame, drawer)
    }
}

@Composable
@Preview
fun NavDrawerGameSelected() {
    NxModsTheme {
        DrawerGameListItem(HomeStates.drawerGameSelected, drawer)
    }
}

@Composable
@Preview
fun NavDrawerGameSelectedDark() {
    NxModsTheme(useDarkTheme = true) {
        DrawerGameListItem(HomeStates.drawerGameSelected, drawer)
    }
}
