package com.sedsoftware.nxmods.domain.type

enum class EndorseStatus(val label: String) {
    UNDEFINED("Undefined"), UNDECIDED("Undecided"), ENDORSED("Endorsed");

    companion object {
        fun fromStr(str: String): EndorseStatus =
            values().firstOrNull { it.label == str } ?: UNDEFINED
    }
}
