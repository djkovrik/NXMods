package com.sedsoftware.nxmods.utils

import com.sedsoftware.nxmods.utils.BBCodeConverter.TagReplacer

class BBCodeConverter {
    private var currentTag: String? = null
    private var currentArg: String? = null
    private var currentContent: String? = null

    fun convertToMarkdown(str: String): String {
        var s = str
        var index = 0
        while (s.indexOf(TAG_PREFIX, index).also { index = it } != -1) {
            val closingIndex = process(s, index, true)
            if (closingIndex == -1) {
                index++
                continue
            }
            s = s.substring(0, index) + currentContent + s.substring(closingIndex)
        }

        var result: String? = removeTrailingWhitespaces(s)
        while (result != null) {
            s = result
            result = removeTrailingWhitespaces(s)
        }

        index = 0
        @Suppress("LoopWithTooManyJumpStatements")
        while (s.indexOf(TAG_PREFIX, index).also { index = it } != -1) {
            val closingIndex = process(s, index, false)
            if (closingIndex == -1) {
                index++
                continue
            }
            if (currentContent == null) {
                val replacement = SIMPLE_SINGLETON_REPLACERS[currentTag]
                s = s.substring(0, index) + replacement + s.substring(closingIndex)
                continue
            }
            val replacer = REPLACERS[currentTag]
            if (replacer == null) {
                index++
                continue
            }
            val processed = replacer.process(currentTag, currentArg, currentContent)
            s = s.substring(0, index) + processed + s.substring(closingIndex)
        }
        return s
    }

    @Suppress("MagicNumber")
    private fun removeTrailingWhitespaces(str: String): String? {
        var s = str
        var index = 0
        var foundTrailingSpace = false
        while (s.indexOf(TAG_PREFIX, index).also { index = it } != -1) {
            val closingIndex = process(s, index, false)
            if (closingIndex == -1 || currentContent == null) {
                index++
                continue
            }
            val startsWithSpace = currentContent!!.startsWith(" ")
            val endsWithSpace = currentContent!!.endsWith(" ")
            if (startsWithSpace || endsWithSpace) {
                foundTrailingSpace = true
                currentContent = currentContent!!.trim { it <= ' ' }
            }

            val tagSuffixIndex = s.indexOf(TAG_SUFFIX, index)
            currentContent =
                s.substring(index, tagSuffixIndex + 1) + currentContent + s.substring(closingIndex - currentTag!!.length - 3, closingIndex)
            if (startsWithSpace) {
                currentContent = " $currentContent"
            }
            if (endsWithSpace) {
                currentContent += " "
            }
            s = s.substring(0, index) + currentContent + s.substring(closingIndex)
            index++
        }
        return if (foundTrailingSpace) s else null
    }

    @Suppress("ReturnCount")
    private fun process(s: String, index: Int, deduplicate: Boolean): Int {
        val tagSuffixIndex = s.indexOf(TAG_SUFFIX, index)
        if (tagSuffixIndex == -1 || tagSuffixIndex == index + 1) {
            return -1
        }
        var tagName = s.substring(index + 1, tagSuffixIndex).lowercase()
        var tagArg: String? = null
        val argIndex = tagName.indexOf(ARG_PREFIX)
        if (argIndex != -1) {
            tagArg = tagName.substring(argIndex + 1)
            tagName = tagName.substring(0, argIndex)
        }
        if (!deduplicate && SIMPLE_SINGLETON_REPLACERS.containsKey(tagName)) {
            currentTag = tagName
            currentArg = null
            currentContent = null
            return tagSuffixIndex + 1
        }
        val lowerCaseString = s.lowercase()
        val closingTag: String = format(CLOSING_FORMAT, tagName)
        val closingIndex = lowerCaseString.indexOf(closingTag, index)
        if (closingIndex == -1) {
            return -1
        }
        currentTag = tagName
        currentArg = tagArg
        currentContent = s.substring(tagSuffixIndex + 1, closingIndex)
        if (deduplicate) {
            val fullTag = s.substring(index, tagSuffixIndex + 1).lowercase()
            val lowerCaseContent = lowerCaseString.substring(tagSuffixIndex + 1, closingIndex)
            val duplicateTagIndex = lowerCaseContent.indexOf(fullTag)
            if (duplicateTagIndex == -1) {
                return -1
            }

            currentContent =
                fullTag + currentContent!!.substring(0, duplicateTagIndex) + currentContent!!.substring(duplicateTagIndex + fullTag.length)
        }
        return closingIndex + closingTag.length
    }

    fun interface TagReplacer {
        fun process(tag: String?, tagArg: String?, content: String?): String
    }

    private fun format(formatter: String, replacement: String): String =
        formatter.replace("%s", replacement)

    companion object {
        private val REPLACERS: MutableMap<String?, TagReplacer> = HashMap()
        private val SIMPLE_SINGLETON_REPLACERS: MutableMap<String?, String> = HashMap()
        private const val CLOSING_FORMAT = "[/%s]"
        private const val TAG_PREFIX = '['
        private const val TAG_SUFFIX = ']'
        private const val ARG_PREFIX = '='

        init {
            REPLACERS["color"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["center"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["u"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["quote"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["font"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["user"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["list"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["size"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! }
            REPLACERS["spoiler"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> content!! } //TODO?
            REPLACERS["b"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> "**$content**" }
            REPLACERS["i"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> "*$content*" }
            REPLACERS["s"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> "~~$content~~" }
            REPLACERS["img"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> "![$content]($content)" }
            REPLACERS["url"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> "[$content]($tagArg)" }
            REPLACERS["code"] = TagReplacer { tag: String?, tagArg: String?, content: String? -> "```$content```" }

            SIMPLE_SINGLETON_REPLACERS["*"] = "* "
        }
    }
}
