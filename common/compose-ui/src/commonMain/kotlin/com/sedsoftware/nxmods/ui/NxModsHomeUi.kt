@file:OptIn(ExperimentalDecomposeApi::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AirlineSeatLegroomNormal
import androidx.compose.material.icons.filled.Announcement
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Cyclone
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.isEnter
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.domain.type.ModListType

@Composable
@Suppress("UnusedPrivateMember")
fun NxModsHomeContent(component: NxModsHome, modifier: Modifier = Modifier) {
    val model: NxModsHome.Model by component.models.subscribeAsState()
    val childStack: ChildStack<*, NxModsHome.Child> by component.childStack.subscribeAsState()
    val currentTab: ModListType = childStack.active.instance.type

    Column(
        modifier = modifier
    ) {
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

        BottomNavigation(modifier = Modifier.fillMaxWidth()) {

            BottomNavigationItem(
                selected = currentTab == ModListType.LATEST_ADDED,
                onClick = component::onLatestAddedTabClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.AirlineSeatLegroomNormal,
                        contentDescription = "Latest added",
                    )
                },
                label = { Text(text = "Latest added", softWrap = false) },
            )

            BottomNavigationItem(
                selected = currentTab == ModListType.LATEST_UPDATED,
                onClick = component::onLatestUpdatedTabClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Chair,
                        contentDescription = "Latest updated",
                    )
                },
                label = { Text(text = "Latest updated", softWrap = false) },
            )

            BottomNavigationItem(
                selected = currentTab == ModListType.TRENDING,
                onClick = component::onTrendingTabClicked,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Cyclone,
                        contentDescription = "Trending",
                    )
                },
                label = { Text(text = "Trending", softWrap = false) },
            )
        }
    }
}

@Composable
private fun tabAnimation(): StackAnimation<Any, NxModsHome.Child> =
    stackAnimation { child, otherChild, direction ->
        val index = child.instance.index
        val otherIndex = otherChild.instance.index
        val anim = slide()
        if ((index > otherIndex) == direction.isEnter) anim else anim.flipSide()
    }
