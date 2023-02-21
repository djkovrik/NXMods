package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.component.HomeUserProfile
import com.sedsoftware.nxmods.ui.stubs.ProfileStates
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Preview
@Composable
fun NormalProfilePreview() {
    NxModsTheme {
        HomeUserProfile(ProfileStates.normal)
    }
}

@Preview
@Composable
fun PremiumProfilePreview() {
    NxModsTheme {
        HomeUserProfile(ProfileStates.premium)
    }
}

@Preview
@Composable
fun SupporterProfilePreview() {
    NxModsTheme {
        HomeUserProfile(ProfileStates.supporter)
    }
}
