package com.sedsoftware.nxmods.markdown.model

interface ReferenceLinkHandler {
    fun store(label: String, destination: String?)
    fun find(label: String): String
}

class ReferenceLinkHandlerImpl : ReferenceLinkHandler {
    private val stored = mutableMapOf<String, String?>()
    override fun store(label: String, destination: String?) {
        stored[label] = destination
    }

    override fun find(label: String): String {
        return stored[label] ?: label
    }
}
