package com.sedsoftware.nxmods.domain.entity

class ModCategory(
    val id: Long,
    val name: String
) {

    companion object {
        fun empty(): ModCategory = ModCategory(-1L, "")
    }
}
