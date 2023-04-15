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
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.outlined.TrackChanges
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.ui.component.toolbar.MinToolbarHeight
import com.sedsoftware.nxmods.ui.component.toolbar.ToolbarButtonSize

@Composable
fun NxAppBarCollapsing(
    title: String,
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
                modifier = modifier
                    .background(Color.Green)
                    .fillMaxSize()
                    .graphicsLayer { alpha = progress * 0.75f }
            )

//            Image(
//                painter = rememberAsyncImagePainter(url = imageUrl),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .graphicsLayer { alpha = progress * Alpha },
//                alignment = BiasAlignment(0f, 1f - ((1f - progress) * 0.75f))
//            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize()
            ) {
                CollapsingToolbarLayout(progress = progress) {

                    // Back arrow
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(ToolbarButtonSize)
                            .background(
                                color = LocalContentColor.current.copy(alpha = 0.0f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Default.ArrowBack,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            contentDescription = null,
                        )
                    }

                    // Title
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleMedium,
                    )

                    // Info buttons
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(ToolbarButtonSize)
                                .background(
                                    color = LocalContentColor.current.copy(alpha = 0.0f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.Outlined.TrackChanges,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(ToolbarButtonSize)
                                .background(
                                    color = LocalContentColor.current.copy(alpha = 0.0f),
                                    shape = CircleShape
                                )
                        ) {
                            Icon(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.Outlined.ThumbUp,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
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
        check(measurables.size == 3)

        val placeables = measurables.map {
            it.measure(constraints)
        }

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {

            val backArrow = placeables[0]
            val title = placeables[1]
            val buttons = placeables[2]

            backArrow.placeRelative(
                x = 0,
                y = (MinToolbarHeight.toPx() / 2.0).toInt() - backArrow.height / 2,
            )

            title.placeRelative(
                x = lerpInt(
                    start = 0,
                    stop = (backArrow.width * 1.5).toInt(),
                    fraction = progress
                ),
                y = lerpInt(
                    start = constraints.maxHeight - (title.height * 1.5).toInt(),
                    stop = (MinToolbarHeight.toPx() / 2.0).toInt() - title.height / 2,
                    fraction = progress
                )
            )

            buttons.placeRelative(
                x = constraints.maxWidth - buttons.width,
                y = lerpInt(
                    start = constraints.maxHeight - (buttons.height * 1.5).toInt(),
                    stop = (MinToolbarHeight.toPx() / 2.0).toInt() - buttons.height / 2,
                    fraction = progress
                )
            )
        }
    }
}

private fun lerpInt(start: Int, stop: Int, fraction: Float): Int =
    (start * fraction + stop * (1f - fraction)).toInt()
