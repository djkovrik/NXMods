package com.sedsoftware.nxmods.utils

import kotlinx.datetime.LocalDateTime
import kotlin.math.abs

fun String.asThumbnail(): String =
    this.replace("images", "images/thumbnails")

@Suppress("MagicNumber")
fun Long.prettify(): String {
    return when (abs(this)) {
        in 1000..999999 -> {
            val primary = (this / 1000).toString()
            val secondary = (this % 1000).toString()
            if (secondary[0] != '0') {
                "$primary.${secondary[0]}k"
            } else {
                "${primary}k"
            }
        }

        in 1000000..999999999 -> {
            val primary = (this / 1000000).toString()
            val secondary = (this % 1000000).toString()
            if (secondary[0] != '0') {
                "$primary.${secondary[0]}M"
            } else {
                "${primary}M"
            }
        }

        in 1000000000..999999999999 -> {
            val primary = (this / 1000000000).toString()
            val secondary = (this % 1000000000).toString()
            if (secondary[0] != '0') {
                "$primary.${secondary[0]}bn"
            } else {
                "${primary}bn"
            }
        }

        else -> this.toString()
    }
}

fun LocalDateTime?.format(): String {
    if (this == null) return ""

    return "${this.dayOfMonth}.${this.monthNumber}.${this.year}, ${this.hour}:${this.minute}"
}
