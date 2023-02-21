@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.domain.type.ModListType
import com.sedsoftware.nxmods.ui.component.HomeNavigationBar
import com.sedsoftware.nxmods.ui.component.HomeNavigationDrawer
import com.sedsoftware.nxmods.ui.component.ShapedSurface
import kotlinx.coroutines.launch

@Composable
fun NxModsHomeContent(component: NxModsHome, modifier: Modifier = Modifier) {
    val model: NxModsHome.Model by component.models.subscribeAsState()
    val childStack: ChildStack<*, NxModsHome.Child> by component.childStack.subscribeAsState()
    val currentTab: ModListType = childStack.active.instance.type
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    HomeNavigationDrawer(
        model = model,
        drawerState = drawerState,
        modifier = modifier
    ) {
        Scaffold(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = model.currentGame,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = modifier.padding(vertical = 16.dp, horizontal = 32.dp)
                        )
                    },
                    modifier = modifier,
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = ""
                            )
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
        ) { paddingValues ->
            ShapedSurface(paddingValues = paddingValues) {
                Column(modifier = modifier) {
                    Children(
                        stack = childStack,
                        modifier = Modifier.weight(weight = 1F),
                        animation = tabAnimation(),
                    ) {
                        when (val child = it.instance) {
                            is NxModsHome.Child.LatestAdded -> NxModsListContent(component = child.component)
                            is NxModsHome.Child.LatestUpdated -> NxModsListContent(component = child.component)
                            is NxModsHome.Child.Trending -> NxModsListContent(component = child.component)
                        }
                    }

                    HomeNavigationBar(component, currentTab, modifier)
                }
            }
        }
    }
}
