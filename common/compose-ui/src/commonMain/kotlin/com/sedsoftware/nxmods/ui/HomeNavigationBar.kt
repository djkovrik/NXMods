package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.domain.type.ModListType
import dev.icerock.moko.resources.compose.stringResource

@Composable
internal fun HomeNavigationBar(
    component: NxModsHome,
    currentTab: ModListType,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp))
    ) {

        NavigationBarItem(
            selected = currentTab == ModListType.LATEST_ADDED,
            onClick = component::onLatestAddedTabClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.RocketLaunch,
                    contentDescription = "Latest added",
                )
            },
            label = {
                Text(
                    text = stringResource(MR.strings.home_tab_latest_added),
                    softWrap = true
                )
            },
        )

        NavigationBarItem(
            selected = currentTab == ModListType.LATEST_UPDATED,
            onClick = component::onLatestUpdatedTabClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.Whatshot,
                    contentDescription = "Latest updated",
                )
            },
            label = {
                Text(
                    text = stringResource(MR.strings.home_tab_latest_updated),
                    softWrap = true
                )
            },
        )

        NavigationBarItem(
            selected = currentTab == ModListType.TRENDING,
            onClick = component::onTrendingTabClicked,
            icon = {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = "Trending",
                )
            },
            label = {
                Text(
                    text = stringResource(MR.strings.home_tab_trending),
                    softWrap = true
                )
            },
        )
    }
}
