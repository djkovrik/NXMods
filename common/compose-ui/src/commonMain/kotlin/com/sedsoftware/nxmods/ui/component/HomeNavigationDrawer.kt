@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui.component

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedsoftware.nxmods.component.home.NxModsHome

@Composable
internal fun HomeNavigationDrawer(
    model: NxModsHome.Model,
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                model.user?.let { HomeUserProfile(it, modifier) }
            }
        }
    ) {
        content()
    }
}
