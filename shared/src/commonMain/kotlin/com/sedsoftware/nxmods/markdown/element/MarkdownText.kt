package com.sedsoftware.nxmods.markdown.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.sedsoftware.nxmods.markdown.utils.LocalMarkdownTypography
import com.sedsoftware.nxmods.markdown.utils.LocalReferenceLinkHandler
import com.sedsoftware.nxmods.markdown.utils.TAG_IMAGE_URL
import com.sedsoftware.nxmods.markdown.utils.TAG_URL
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal fun MarkdownText(
    content: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalMarkdownTypography.current.text
) {
    MarkdownText(content = AnnotatedString(content), modifier = modifier, style = style)
}

@Composable
internal fun MarkdownText(
    content: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalMarkdownTypography.current.text
) {
    val uriHandler = LocalUriHandler.current
    val referenceLinkHandler = LocalReferenceLinkHandler.current
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    val hasUrl = content.getStringAnnotations(TAG_URL, 0, content.length).any()
    val textModifier = if (hasUrl) modifier.pointerInput(Unit) {
        detectTapGestures { pos ->
            layoutResult.value?.let { layoutResult ->
                val position = layoutResult.getOffsetForPosition(pos)
                content.getStringAnnotations(TAG_URL, position, position)
                    .firstOrNull()
                    ?.let { uriHandler.openUri(referenceLinkHandler.find(it.item)) }
            }
        }
    } else {
        modifier
    }

    Text(
        text = content,
        modifier = textModifier,
        style = style,
        inlineContent = mapOf(
            TAG_IMAGE_URL to InlineTextContent(
                Placeholder(180.sp, 180.sp, PlaceholderVerticalAlign.Bottom)
            ) { link ->
                Image(
                    painter = rememberAsyncImagePainter(url = link),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = modifier.fillMaxWidth()
                )
            }
        ),
        onTextLayout = { layoutResult.value = it }
    )
}
