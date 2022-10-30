package com.sedsoftware.nxmods.domain.entity

class OwnProfile(
    val userId: Long,
    val key: String,
    val name: String,
    val isPremium: Boolean,
    val isSupporter: Boolean,
    val email: String,
    val avatar: String
)
