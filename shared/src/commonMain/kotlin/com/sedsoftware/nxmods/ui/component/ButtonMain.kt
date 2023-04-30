package com.sedsoftware.nxmods.ui.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.ui.component.modifier.bounceClick
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun ButtonMain(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                delay(150L)
                onClick.invoke()
            }
        },
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier
            .defaultMinSize(minWidth = 120.dp)
            .bounceClick()
    ) {
        content()
    }
}
