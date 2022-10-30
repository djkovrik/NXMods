package com.sedsoftware.nxmods.domain.entity

import kotlinx.datetime.LocalDateTime

data class ModInfo(
    val modId: Long,
    val gameId: Long,
    val domainName: String,
    val name: String,
    val summary: String,
    val version: String,
    val description: String,
    val pictureUrl: String,
    val modDownloads: Long,
    val modDownloadsUnique: Long,
    val uid: Long,
    val allowRating: Boolean,
    val categoryId: Long,
    val endorsementCount: Long,
    val createdTimestamp: Long,
    val createdTime: LocalDateTime,
    val updatedTimestamp: Long,
    val updatedTime: LocalDateTime,
    val author: String,
    val uploadedBy: String,
    val uploaderProfileUrl: String,
    val containsAdultContent: Boolean,
    val status: String,
    val available: Boolean,
    val user: UserInfo,
    val isTracked: Boolean,
    val isEndorsed: Boolean,
    val endorsement: Endorsement
)
