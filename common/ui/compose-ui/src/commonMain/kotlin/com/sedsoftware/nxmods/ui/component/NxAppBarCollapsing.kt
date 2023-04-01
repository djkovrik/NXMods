package com.sedsoftware.nxmods.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.rounded.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private val ContentPadding = 8.dp
private val ButtonSize = 24.dp
private const val Alpha = 0.75f

@Composable
fun NxAppBarCollapsing(
    imageUrl: String,
    progress: Float,
    modifier: Modifier = Modifier
) {

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
                    .graphicsLayer { alpha = progress * Alpha }
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = ContentPadding)
                    .fillMaxSize()
            ) {
                CollapsingToolbarLayout(progress = progress) {
                    // Back button
                    IconButton(
                        onClick = { },
                        modifier = modifier
                            .size(ButtonSize)
                            .background(
                                color = LocalContentColor.current.copy(alpha = 0.0f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            modifier = modifier,
                            contentDescription = null,
                        )
                    }

                    // Endorse + track buttons
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(ContentPadding)
                    ) {
                        IconButton(
                            onClick = { },
                            modifier = modifier
                                .size(ButtonSize)
                                .background(
                                    color = LocalContentColor.current.copy(alpha = 0.0f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ThumbUp,
                                modifier = modifier,
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = { },
                            modifier = modifier
                                .size(ButtonSize)
                                .background(
                                    color = LocalContentColor.current.copy(alpha = 0.0f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.TrackChanges,
                                modifier = modifier,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CollapsingToolbarLayout(
    progress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 2) // [0]: Back button, [1]: Buttons

        val placeables = measurables.map {
            it.measure(constraints)
        }
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {

            val collapsedHorizontalGuideline = (constraints.maxHeight * 0.5f).roundToInt()

            val backButton = placeables[0]
            val buttons = placeables[1]

            backButton.placeRelative(
                x = 0,
                y = collapsedHorizontalGuideline - backButton.height / 2,
            )

            buttons.placeRelative(
                x = constraints.maxWidth - buttons.width,
                y = cLerp(
                    start = (constraints.maxHeight - buttons.height) / 2,
                    stop = 0,
                    fraction = progress
                )
            )
        }
    }
}

private fun cLerp(start: Int, stop: Int, fraction: Float): Int =
    (start * fraction + stop * (1f - fraction)).toInt()


//            Image(
//                painter = rememberAsyncImagePainter(url = imageUrl),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .graphicsLayer { alpha = progress * Alpha },
//                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
//            )
