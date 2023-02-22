package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.component.SplashLogo
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Preview
@Composable
fun SplashPreview() {
    NxModsTheme {
        SplashLogo()
    }
}
