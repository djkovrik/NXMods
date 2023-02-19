package com.sedsoftware.nxmods.ui.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonMain(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier.defaultMinSize(minWidth = 120.dp)
    ) {
        content()
    }
}
