package com.sedsoftware.nxmods.markdown

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.flavours.MarkdownFlavourDescriptor
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser


@Composable
fun NxMarkdown(
    markdown: String,
    modifier: Modifier = Modifier
) {

    val flavour: MarkdownFlavourDescriptor = remember { CommonMarkFlavourDescriptor() }
    val markdownTree: ASTNode = remember { MarkdownParser(flavour).buildMarkdownTreeFromString(markdown) }

    Column(modifier = modifier) {
        markdownTree.children.forEach { node ->
            if (!node.handleElement(markdown)) {
                node.children.forEach { child ->
                    child.handleElement(markdown)
                }
            }
        }
    }
}

@Composable
private fun ASTNode.handleElement(content: String): Boolean {
    val typography = MaterialTheme.typography
    var handled = true
    when (type) {
        MarkdownElementTypes.UNORDERED_LIST -> TODO()
        MarkdownElementTypes.ORDERED_LIST -> TODO()
        MarkdownElementTypes.BLOCK_QUOTE -> TODO()
        MarkdownElementTypes.CODE_FENCE -> TODO()
        MarkdownElementTypes.CODE_BLOCK -> TODO()
        MarkdownElementTypes.PARAGRAPH -> TODO()
        MarkdownElementTypes.LINK_DEFINITION -> TODO()
        MarkdownElementTypes.IMAGE -> TODO()
        MarkdownElementTypes.ATX_1 -> TODO()
        MarkdownElementTypes.ATX_2 -> TODO()
        MarkdownElementTypes.ATX_3 -> TODO()
        MarkdownElementTypes.ATX_4 -> TODO()
        MarkdownElementTypes.ATX_5 -> TODO()
        MarkdownElementTypes.ATX_6 -> TODO()
        MarkdownTokenTypes.Companion.TEXT -> TODO()
        MarkdownTokenTypes.Companion.EOL -> {}
        else -> handled = false
    }

    return handled
}
