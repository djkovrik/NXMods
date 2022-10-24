package com.sedsoftware.nxmods.domain.entity

class Endorsement(
    val endorseStatus: EndorseStatus,
    val timestamp: Long,
    val version: String
) {

    companion object {
        fun empty(): Endorsement =
            Endorsement(EndorseStatus.UNDEFINED, -1L, "")
    }
}
