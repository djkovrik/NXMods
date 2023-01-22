package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.domain.entity.GameInfo

@Composable
fun NxModsGameSelectorContent(component: NxModsGameSelector) {
    val model: NxModsGameSelector.Model by component.models.subscribeAsState()
    NxModsGameSelectorScreen(model)
}

@Composable
fun NxModsGameSelectorScreen(
    model: NxModsGameSelector.Model,
    modifier: Modifier = Modifier,
    onNextClicked: () -> Unit = {},
    onBookmarkClicked: (String) -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.fillMaxSize(),
    ) {
        Text(text = "Please select games you want to track: ")

        Row {
            Button(
                onClick = onNextClicked,
                enabled = model.nextButtonAvailable,
            ) {
                Text(text = "Next")
            }

            if (model.progressVisible) {
                CircularProgressIndicator()
            }
        }

        LazyColumn {
            items(model.games) { item ->
                NxModsGameItem(
                    game = item,
                    modifier = modifier,
                    onChecked = onBookmarkClicked
                )
            }
        }
    }
}

@Composable
fun NxModsGameItem(
    game: GameInfo,
    modifier: Modifier = Modifier,
    onChecked: (String) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = game.isBookmarked,
            onCheckedChange = { onChecked.invoke(game.domain) }
        )
        Text(
            text = game.name,
            fontSize = 18.sp
        )
    }
}
