package com.sedsoftware.nxmods.markdown.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
interface MarkdownColors {
    val text: Color
    val backgroundCode: Color
}

@Immutable
private class DefaultMarkdownColors(
    override val text: Color,
    override val backgroundCode: Color
) : MarkdownColors

@Composable
fun markdownColor(
    text: Color = MaterialTheme.colorScheme.onSurface,
    backgroundCode: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
): MarkdownColors = DefaultMarkdownColors(
    text = text,
    backgroundCode = backgroundCode
)
