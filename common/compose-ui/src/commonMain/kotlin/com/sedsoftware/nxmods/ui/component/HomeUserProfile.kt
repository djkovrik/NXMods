package com.sedsoftware.nxmods.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.component.home.model.CurrentUser
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun HomeUserProfile(
    user: CurrentUser,
    modifier: Modifier = Modifier,
    onPreferenceClick: () -> Unit = {},
) {
    Row(modifier = modifier.padding(all = 16.dp)) {
        Box(
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(url = user.avatar),
                contentDescription = null
            )
        }
        Column(modifier = modifier
            .padding(horizontal = 16.dp)
            .weight(1f)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            if (user.isPremium) {
                Text(
                    text = "Premium",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            } else if (user.isSupporter) {
                Text(
                    text = "Supporter",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        IconButton(
            onClick = onPreferenceClick,
            modifier = modifier
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .size(40.dp)
                .align(Alignment.Top)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = null
            )
        }
    }
}
