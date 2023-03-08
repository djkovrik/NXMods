package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceCategory
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.ui.component.preference.PreferenceCategory
import com.sedsoftware.nxmods.ui.component.preference.PreferenceSwitcher
import com.sedsoftware.nxmods.ui.stubs.PrefsStubs
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Preview
@Composable
fun PreferenceCategoryPreview() {
    NxModsTheme {
        PreferenceCategory(NxPreferenceCategory.CONTENT)
    }
}

@Preview
@Composable
fun PreferenceSwitcherPreviewChecked() {
    NxModsTheme {
        PreferenceSwitcher(
            key = NxPreferenceKeyUnique.ENABLE_NSWF,
            value = true
        )
    }
}

@Preview
@Composable
fun PreferenceSwitcherPreviewUnchecked() {
    NxModsTheme {
        PreferenceSwitcher(
            key = NxPreferenceKeyUnique.ENABLE_NSWF,
            value = false
        )
    }
}
