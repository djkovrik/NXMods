package com.sedsoftware.nxmods.markdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sedsoftware.nxmods.markdown.element.MarkdownBlockQuote
import com.sedsoftware.nxmods.markdown.element.MarkdownBulletList
import com.sedsoftware.nxmods.markdown.element.MarkdownCodeBlock
import com.sedsoftware.nxmods.markdown.element.MarkdownCodeFence
import com.sedsoftware.nxmods.markdown.element.MarkdownHeader
import com.sedsoftware.nxmods.markdown.element.MarkdownImage
import com.sedsoftware.nxmods.markdown.element.MarkdownOrderedList
import com.sedsoftware.nxmods.markdown.element.MarkdownParagraph
import com.sedsoftware.nxmods.markdown.element.MarkdownText
import com.sedsoftware.nxmods.markdown.model.MarkdownColors
import com.sedsoftware.nxmods.markdown.model.MarkdownPadding
import com.sedsoftware.nxmods.markdown.model.MarkdownTypography
import com.sedsoftware.nxmods.markdown.model.ReferenceLinkHandlerImpl
import com.sedsoftware.nxmods.markdown.model.markdownColor
import com.sedsoftware.nxmods.markdown.model.markdownPadding
import com.sedsoftware.nxmods.markdown.model.markdownTypography
import com.sedsoftware.nxmods.markdown.utils.LocalMarkdownColors
import com.sedsoftware.nxmods.markdown.utils.LocalMarkdownPadding
import com.sedsoftware.nxmods.markdown.utils.LocalMarkdownTypography
import com.sedsoftware.nxmods.markdown.utils.LocalReferenceLinkHandler
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.ASTNode
import org.intellij.markdown.ast.findChildOfType
import org.intellij.markdown.ast.getTextInNode
import org.intellij.markdown.flavours.MarkdownFlavourDescriptor
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.parser.MarkdownParser

@Composable
fun NxMarkdown(
    markdown: String,
    colors: MarkdownColors = markdownColor(),
    typography: MarkdownTypography = markdownTypography(),
    padding: MarkdownPadding = markdownPadding(),
    modifier: Modifier = Modifier.fillMaxSize(),
) {

    val flavour: MarkdownFlavourDescriptor = remember { CommonMarkFlavourDescriptor() }
    val markdownTree: ASTNode = MarkdownParser(flavour).buildMarkdownTreeFromString(markdown)

    CompositionLocalProvider(
        LocalReferenceLinkHandler provides ReferenceLinkHandlerImpl(),
        LocalMarkdownPadding provides padding,
        LocalMarkdownColors provides colors,
        LocalMarkdownTypography provides typography,
    ) {
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
}

@Composable
@Suppress("ComplexMethod")
private fun ASTNode.handleElement(content: String): Boolean {
    val typography = LocalMarkdownTypography.current
    var handled = true
    Spacer(modifier = Modifier.height(LocalMarkdownPadding.current.block))
    when (type) {
        MarkdownElementTypes.UNORDERED_LIST -> {
            Column(modifier = Modifier) {
                MarkdownBulletList(content, this@handleElement, style = typography.bullet)
            }

        }

        MarkdownElementTypes.ORDERED_LIST -> {
            Column(modifier = Modifier) {
                MarkdownOrderedList(content, this@handleElement, style = typography.ordered)
            }
        }

        MarkdownElementTypes.BLOCK_QUOTE -> {
            MarkdownBlockQuote(content, this)
        }

        MarkdownElementTypes.CODE_FENCE -> {
            MarkdownCodeFence(content, this)
        }

        MarkdownElementTypes.CODE_BLOCK -> {
            MarkdownCodeBlock(content, this)
        }

        MarkdownElementTypes.PARAGRAPH -> {
            MarkdownParagraph(content, this, style = typography.paragraph)
        }

        MarkdownElementTypes.LINK_DEFINITION -> {
            val linkLabel = findChildOfType(MarkdownElementTypes.LINK_LABEL)?.getTextInNode(content)?.toString()
            if (linkLabel != null) {
                val destination = findChildOfType(MarkdownElementTypes.LINK_DESTINATION)?.getTextInNode(content)?.toString()
                LocalReferenceLinkHandler.current.store(linkLabel, destination)
            }
        }

        MarkdownElementTypes.IMAGE -> {
            MarkdownImage(content, this)
        }

        MarkdownElementTypes.ATX_1 -> {
            MarkdownHeader(content, this, typography.h1)
        }

        MarkdownElementTypes.ATX_2 -> {
            MarkdownHeader(content, this, typography.h2)
        }

        MarkdownElementTypes.ATX_3 -> {
            MarkdownHeader(content, this, typography.h3)
        }

        MarkdownElementTypes.ATX_4 -> {
            MarkdownHeader(content, this, typography.h4)
        }

        MarkdownElementTypes.ATX_5 -> {
            MarkdownHeader(content, this, typography.h5)
        }

        MarkdownElementTypes.ATX_6 -> {
            MarkdownHeader(content, this, typography.h6)
        }

        MarkdownTokenTypes.Companion.TEXT -> {
            MarkdownText(content = getTextInNode(content).toString())
        }

        MarkdownTokenTypes.Companion.EOL -> {}
        else -> {
            handled = false
        }
    }

    return handled
}
