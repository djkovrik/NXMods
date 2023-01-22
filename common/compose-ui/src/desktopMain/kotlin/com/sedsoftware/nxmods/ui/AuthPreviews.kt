package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.stubs.AuthStates

@Preview
@Composable
fun PreviewNewUser() {
    NxModsAuthScreen(
        model = AuthStates.newUserKeyValidating
    )
}
