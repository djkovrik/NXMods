package com.sedsoftware.nxmods.ui.component.preference

import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceCategory
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.ui.MR
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun NxPreferenceCategory.asLabel(): String =
    when (this) {
        NxPreferenceCategory.CONTENT -> stringResource(MR.strings.preferences_category_content)
        else -> ""
    }

@Composable
fun NxPreferenceKeyUnique.asLabel(): String =
    when (this) {
        NxPreferenceKeyUnique.ENABLE_NSWF -> stringResource(MR.strings.preferences_option_nsfw)
        NxPreferenceKeyUnique.MANAGE_GAMES -> stringResource(MR.strings.preferences_option_game_selector)
        else -> ""
    }
