@file:OptIn(ExperimentalMaterial3Api::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.component.home.model.NavDrawerGame
import com.sedsoftware.nxmods.domain.type.ModListType
import com.sedsoftware.nxmods.ui.component.HomeNavigationDrawer
import com.sedsoftware.nxmods.ui.component.ShapedSurface
import kotlinx.coroutines.launch

@Composable
fun NxModsHomeContent(component: NxModsHome) {
    val model: NxModsHome.Model by component.models.subscribeAsState()
    val childStack: ChildStack<*, NxModsHome.Child> by component.childStack.subscribeAsState()
    NxModsHomeScreen(
        model = model,
        childStack = childStack,
        onGameSwitched = component::onDrawerGameClicked,
        onLatestAddedClicked = component::onLatestAddedTabClicked,
        onLatestUpdatedClicked = component::onLatestUpdatedTabClicked,
        onTrendingClicked = component::onTrendingTabClicked,
        onPreferencesRequested = component::onPreferenceIconClicked,
        onNavDrawerRequested = component::onNavDrawerRequested
    )
}

@Composable
fun NxModsHomeScreen(
    model: NxModsHome.Model,
    childStack: ChildStack<*, NxModsHome.Child>,
    modifier: Modifier = Modifier,
    onGameSwitched: (NavDrawerGame) -> Unit = {},
    onLatestAddedClicked: () -> Unit,
    onLatestUpdatedClicked: () -> Unit,
    onTrendingClicked: () -> Unit,
    onPreferencesRequested: () -> Unit = {},
    onNavDrawerRequested: (Boolean) -> Unit = {}
) {

    val currentTab: ModListType = childStack.active.instance.type
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
        confirmStateChange = { drawerValue ->
            onNavDrawerRequested.invoke(drawerValue == DrawerValue.Open)
            true
        }
    )

    LaunchedEffect(model.navDrawerVisible) {
        if (model.navDrawerVisible && drawerState.isClosed) {
            drawerState.open()
        }
        if (!model.navDrawerVisible && drawerState.isOpen) {
            drawerState.close()
        }
    }

    HomeNavigationDrawer(
        model = model,
        drawerState = drawerState,
        onGameSwitched = onGameSwitched,
        onPreferencesRequested = onPreferencesRequested,
        modifier = modifier,
    ) {

        val animatedRotation = remember { Animatable(0f) }

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
                            onClick = { onNavDrawerRequested.invoke(true) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = ""
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                when (val child = childStack.active.instance) {
                                    is NxModsHome.Child.LatestAdded -> child.component.onRefreshRequest()
                                    is NxModsHome.Child.LatestUpdated -> child.component.onRefreshRequest()
                                    is NxModsHome.Child.Trending -> child.component.onRefreshRequest()
                                }

                                scope.launch {
                                    animatedRotation.animateTo(
                                        targetValue = animatedRotation.value + 360f,
                                        animationSpec = tween(durationMillis = 300, easing = LinearEasing)
                                    )
                                }
                            },
                            modifier = modifier.rotate(animatedRotation.value)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "",
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
                        modifier = Modifier.weight(weight = 1f),
                        animation = tabAnimation(),
                    ) {
                        when (val child = it.instance) {
                            is NxModsHome.Child.LatestAdded -> NxModsListContent(component = child.component)
                            is NxModsHome.Child.LatestUpdated -> NxModsListContent(component = child.component)
                            is NxModsHome.Child.Trending -> NxModsListContent(component = child.component)
                        }
                    }

                    HomeNavigationBar(
                        onLatestAddedClicked = onLatestAddedClicked,
                        onLatestUpdatedClicked = onLatestUpdatedClicked,
                        onTrendingClicked = onTrendingClicked,
                        currentTab = currentTab,
                        modifier = modifier
                    )
                }
            }
        }
    }
}
