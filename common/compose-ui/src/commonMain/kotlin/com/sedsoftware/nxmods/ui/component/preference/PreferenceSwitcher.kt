package com.sedsoftware.nxmods.ui.component.preference

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique

@Composable
fun PreferenceSwitcher(
    key: NxPreferenceKeyUnique,
    value: Boolean,
    modifier: Modifier = Modifier,
    onSwitchChange: (NxPreferenceKeyUnique, Boolean) -> Unit = { _, _ -> }
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    ) {

        Text(
            text = key.asLabel(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier.weight(1f)
        )

        Switch(
            checked = value,
            onCheckedChange = { checked ->
                onSwitchChange.invoke(key, checked)
            },
            colors = SwitchDefaults.colors(

            ),
            modifier = modifier
        )
    }
}
