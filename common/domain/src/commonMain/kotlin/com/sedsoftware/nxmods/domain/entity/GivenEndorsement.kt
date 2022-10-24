package com.sedsoftware.nxmods.domain.entity

import kotlinx.datetime.LocalDateTime

class GivenEndorsement(
    val id: Long,
    val domain: String,
    val date: LocalDateTime,
    val version: String,
    val status: EndorseStatus
)
