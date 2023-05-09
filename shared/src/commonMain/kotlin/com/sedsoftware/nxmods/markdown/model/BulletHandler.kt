package com.sedsoftware.nxmods.markdown.model

internal fun interface BulletHandler {
    fun transform(bullet: CharSequence?): String
}
