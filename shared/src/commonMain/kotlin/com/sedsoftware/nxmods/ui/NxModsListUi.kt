package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.MainRes
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.component.modlist.model.ModInfoModel
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun NxModsListContent(component: NxModsList) {
    val model: NxModsList.Model by component.models.subscribeAsState()
    NxModsListScreen(
        model = model,
        onModClicked = component::onModInfoClick
    )
}

@Composable
internal fun NxModsListScreen(
    model: NxModsList.Model,
    modifier: Modifier = Modifier,
    onModClicked: (String, Long, Long) -> Unit = { _, _, _ -> }
) {

    if (!model.progressVisible) {
        LazyColumn(
            modifier = modifier
                .padding(all = 8.dp)
                .fillMaxSize()
        ) {
            itemsIndexed(model.mods) { index, item ->
                ModListItem(
                    item = item,
                    index = index,
                    modifier = modifier
                        .clickable { onModClicked.invoke(item.domain, item.modId, item.categoryId) }
                )

                if (index != model.mods.lastIndex) {
                    Spacer(modifier = modifier.height(8.dp))
                }
            }
        }
    } else {
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

    if (model.emptyListPlaceholderVisible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = MainRes.string.mods_list_empty_placeholder,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Composable
internal fun ModListItem(
    item: ModInfoModel,
    index: Int,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 1.4f)
            .clip(
                shape = if (index != 0) {
                    RoundedCornerShape(size = 8.dp)
                } else {
                    RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp, bottomStart = 8.dp, bottomEnd = 8.dp)
                }
            )
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Image(
            painter = rememberAsyncImagePainter(url = item.picture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )

        Box(
            modifier = modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface.copy(
                                alpha = 0.6f
                            ),
                            MaterialTheme.colorScheme.surface.copy(
                                alpha = 0.8f
                            ),
                        )
                    ),
                )
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(all = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = item.summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${item.category} | by ${item.author}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        tint = MaterialTheme.colorScheme.tertiary,
                        contentDescription = "",
                        modifier = modifier
                            .size(20.dp)
                    )
                    Text(
                        text = item.endorsements,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = modifier.padding(horizontal = 4.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Download,
                        tint = MaterialTheme.colorScheme.tertiary,
                        contentDescription = "",
                        modifier = modifier
                            .size(20.dp)
                    )
                    Text(
                        text = item.downloads,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.tertiary,
                        modifier = modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
    }
}
