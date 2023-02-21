package com.sedsoftware.nxmods.domain.entity

data class GameInfo(
    val id: Long,
    val name: String,
    val forumUrl: String,
    val nexusmodsUrl: String,
    val genre: String,
    val filesCount: Long,
    val downloadsCount: Long,
    val domain: String,
    val filesViews: Long,
    val authors: Long,
    val fileEndorsements: Long,
    val modsCount: Long,
    val isBookmarked: Boolean,
    val categories: List<ModCategory>
)
