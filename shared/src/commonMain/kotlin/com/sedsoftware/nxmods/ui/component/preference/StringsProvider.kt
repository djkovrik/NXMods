package com.sedsoftware.nxmods.ui.component.preference

import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.MainRes
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceCategory
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique

@Composable
fun NxPreferenceCategory.asLabel(): String =
    when (this) {
        NxPreferenceCategory.CONTENT -> MainRes.string.preferences_category_content
        else -> ""
    }

@Composable
fun NxPreferenceKeyUnique.asLabel(): String =
    when (this) {
        NxPreferenceKeyUnique.ENABLE_NSWF -> MainRes.string.preferences_option_nsfw
        NxPreferenceKeyUnique.MANAGE_GAMES -> MainRes.string.preferences_option_game_selector
        else -> ""
    }
