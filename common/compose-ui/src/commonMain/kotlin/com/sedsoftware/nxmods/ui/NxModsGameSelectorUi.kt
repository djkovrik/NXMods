@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileCopy
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.component.gameselector.model.GameInfoModel
import com.sedsoftware.nxmods.ui.component.ButtonMain
import com.sedsoftware.nxmods.ui.component.RoundCheckbox
import com.sedsoftware.nxmods.ui.component.ShapedSurface
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun NxModsGameSelectorContent(component: NxModsGameSelector) {
    val model: NxModsGameSelector.Model by component.models.subscribeAsState()
    NxModsGameSelectorScreen(
        model = model,
        onNextClicked = component::onNextButtonClicked,
        onBookmarkClicked = component::onBookmarkClicked,
        onSearchClicked = component::onSearchButtonClicked,
        onSearchCloseClicked = component::onSearchCloseButtonClicked,
        onSearchInput = component::onSearchTextInput
    )
}

@Composable
@Suppress("LongMethod")
internal fun NxModsGameSelectorScreen(
    model: NxModsGameSelector.Model,
    modifier: Modifier = Modifier,
    onNextClicked: () -> Unit = {},
    onBookmarkClicked: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onSearchCloseClicked: () -> Unit = {},
    onSearchInput: (String) -> Unit = {},
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        topBar = {
            if (model.searchVisible) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, top = 5.dp, bottom = 4.dp)
                ) {
                    OutlinedTextField(
                        value = model.searchQuery,
                        modifier = modifier.weight(1f),
                        onValueChange = onSearchInput,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            autoCorrect = false
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { keyboardController?.hide() }
                        ),
                        shape = MaterialTheme.shapes.medium
                    )
                    IconButton(
                        onClick = {
                            onSearchCloseClicked.invoke()
                            keyboardController?.hide()
                        },
                        modifier = modifier.padding(all = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Column(modifier = modifier.weight(1f)) {
                        Text(
                            text = stringResource(MR.strings.game_selector_header),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp)
                        )
                        Text(
                            text = "${stringResource(MR.strings.game_selector_sub_header)} ${model.bookmarkedCounter}",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = modifier.padding(start = 32.dp, end = 32.dp, bottom = 16.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            onSearchClicked.invoke()
                            keyboardController?.show()
                        },
                        modifier = modifier.padding(all = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        ShapedSurface(paddingValues = paddingValues) {
            Box {
                when {
                    model.progressVisible -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.primary,
                                modifier = modifier
                            )
                        }
                    }

                    model.emptyPlaceholderVisible -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Text(
                                text = stringResource(MR.strings.game_selector_empty),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = modifier
                                .padding(top = 8.dp, bottom = 8.dp)
                                .fillMaxSize()
                        ) {
                            itemsIndexed(model.games) { index, item ->
                                NxModsGameItem(
                                    model = item,
                                    modifier = modifier,
                                    onChecked = onBookmarkClicked
                                )
                                if (index != model.games.lastIndex) {
                                    Divider(
                                        color = MaterialTheme.colorScheme.secondary,
                                        thickness = 0.5.dp,
                                        modifier = modifier
                                            .padding(horizontal = 16.dp)
                                            .alpha(0.5f)
                                    )
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(
                    visible = model.nextButtonAvailable,
                    enter = fadeIn() + scaleIn(),
                    exit = scaleOut() + fadeOut(),
                    modifier = modifier.align(Alignment.BottomCenter)
                ) {
                    ButtonMain(
                        onClick = onNextClicked,
                        enabled = true,
                        modifier = modifier.padding(all = 16.dp)
                    ) {
                        Text(
                            text = stringResource(MR.strings.game_selector_next),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Column(modifier = modifier.weight(1f)) {
            Text(
                text = model.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                maxLines = 1,
                modifier = modifier.padding(vertical = 8.dp)
            )
            Text(
                text = model.genre,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = modifier
            )
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End,
            modifier = modifier
        ) {
            RoundCheckbox(
                size = 24f,
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                checkmarkColor = MaterialTheme.colorScheme.onPrimary,
                isChecked = model.bookmarked,
                onValueChange = { onChecked.invoke(model.domain) },
                modifier = modifier.padding(vertical = 8.dp)
            )
            Row {
                Icon(
                    imageVector = Icons.Default.FileCopy,
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = "",
                    modifier = modifier.size(20.dp)
                )
                Text(
                    text = model.mods,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.FileDownload,
                    tint = MaterialTheme.colorScheme.tertiary,
                    contentDescription = "",
                    modifier = modifier.size(20.dp)
                )
                Text(
                    text = model.downloads,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
    }
}
