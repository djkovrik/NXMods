package com.sedsoftware.nxmods.markdown.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.sedsoftware.nxmods.markdown.utils.findChildOfTypeRecursive
import com.seiko.imageloader.rememberAsyncImagePainter
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.getTextInNode

@Composable
internal fun MarkdownImage(content: String, node: ASTNode) {
    val link = node.findChildOfTypeRecursive(MarkdownElementTypes.LINK_DESTINATION)?.getTextInNode(content)?.toString() ?: return
    Image(
        painter = rememberAsyncImagePainter(url = link),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth()
    )
}
