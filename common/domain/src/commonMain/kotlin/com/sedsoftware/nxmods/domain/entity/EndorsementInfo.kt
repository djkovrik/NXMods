package com.sedsoftware.nxmods.domain.entity

import com.sedsoftware.nxmods.domain.type.EndorseStatus

class EndorsementInfo(
    val modId: Long,
    val domain: String,
    val status: EndorseStatus
)
