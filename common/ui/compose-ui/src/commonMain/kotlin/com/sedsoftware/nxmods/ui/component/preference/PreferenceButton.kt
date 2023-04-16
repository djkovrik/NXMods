package com.sedsoftware.nxmods.ui.component.preference

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique

@Composable
fun PreferenceButton(
    key: NxPreferenceKeyUnique,
    modifier: Modifier = Modifier,
    onClick: (NxPreferenceKeyUnique) -> Unit = {}
) {

    Box(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick.invoke(key) }
    ) {

        Text(
            text = key.asLabel(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}
