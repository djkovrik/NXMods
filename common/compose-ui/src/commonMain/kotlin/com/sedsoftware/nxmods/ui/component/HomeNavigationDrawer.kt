@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.component.home.model.NavDrawerGame
import kotlinx.coroutines.launch

@Composable
internal fun HomeNavigationDrawer(
    model: NxModsHome.Model,
    drawerState: DrawerState,
    onGameSwitched: (NavDrawerGame) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                model.user?.let { HomeUserProfile(it, modifier) }
                Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
                LazyColumn(
                    contentPadding = PaddingValues(all = 16.dp)
                ) {
                    items(model.games) { game ->
                        DrawerGameListItem(
                            game = game,
                            drawerState = drawerState,
                            modifier = modifier,
                            onGameSwitched = onGameSwitched
                        )
                    }
                }
            }
        }
    ) {
        content()
    }
}

@Composable
internal fun DrawerGameListItem(
    game: NavDrawerGame,
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    onGameSwitched: (NavDrawerGame) -> Unit = {},
) {

    val scope = rememberCoroutineScope()

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = if (game.selected) {
            CardDefaults.cardColors()
        } else {
            CardDefaults.cardColors(
                containerColor = Color.Transparent,
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                scope.launch {
                    drawerState.close()
                }
                onGameSwitched.invoke(game)
            }
    ) {
        Text(
            text = game.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
