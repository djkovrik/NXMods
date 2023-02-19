package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.component.gameselector.model.GameInfoModel
import com.sedsoftware.nxmods.ui.component.RoundCheckbox

@Composable
fun NxModsGameSelectorContent(component: NxModsGameSelector) {
    val model: NxModsGameSelector.Model by component.models.subscribeAsState()
    NxModsGameSelectorScreen(
        model = model,
        onNextClicked = component::onNextButtonClicked,
        onBookmarkClicked = component::onBookmarkClicked
    )
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
                CircularProgressIndicator(
                    modifier = modifier
                )
            }
        }

        LazyColumn {
            items(model.games) { item ->
                NxModsGameItem(
                    model = item,
                    modifier = modifier,
                    onChecked = onBookmarkClicked
                )
            }
        }
    }
}

@Composable
fun NxModsGameItem(
    model: GameInfoModel,
    modifier: Modifier = Modifier,
    onChecked: (String) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Column {
            Text(text = model.name)
            Text(text = model.genre)
        }

        Column {
            RoundCheckbox(
                size = 24f,
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                onValueChange = { onChecked.invoke(model.domain) },
                modifier = modifier
            )
            Row {
                Text(text = "Mods: ${model.mods}")
                Text(text = "Downloads: ${model.downloads}")
            }
        }
    }
}

//Checkbox(
//checked = game.isBookmarked,
//onCheckedChange = { onChecked.invoke(game.domain) }
//)
//Text(
//text = game.name,
//fontSize = 18.sp
//)
