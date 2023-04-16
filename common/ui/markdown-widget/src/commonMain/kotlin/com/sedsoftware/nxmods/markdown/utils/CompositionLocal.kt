package com.sedsoftware.nxmods.markdown.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.sedsoftware.nxmods.markdown.model.BulletHandler
import com.sedsoftware.nxmods.markdown.model.MarkdownColors
import com.sedsoftware.nxmods.markdown.model.MarkdownPadding
import com.sedsoftware.nxmods.markdown.model.MarkdownTypography
import com.sedsoftware.nxmods.markdown.model.ReferenceLinkHandler

internal val LocalBulletListHandler = staticCompositionLocalOf {
    return@staticCompositionLocalOf BulletHandler { "• " }
}

internal val LocalOrderedListHandler = staticCompositionLocalOf {
    return@staticCompositionLocalOf BulletHandler { "$it " }
}

internal val LocalReferenceLinkHandler = staticCompositionLocalOf<ReferenceLinkHandler> {
    error("CompositionLocal ReferenceLinkHandler not present")
}

internal val LocalMarkdownColors = compositionLocalOf<MarkdownColors> {
    error("No local MarkdownColors")
}

internal val LocalMarkdownTypography = compositionLocalOf<MarkdownTypography> {
    error("No local MarkdownTypography")
}

internal val LocalMarkdownPadding = staticCompositionLocalOf<MarkdownPadding> {
    error("No local Padding")
}
