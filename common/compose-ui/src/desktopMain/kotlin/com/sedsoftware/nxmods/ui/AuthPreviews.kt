package com.sedsoftware.nxmods.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.sedsoftware.nxmods.ui.stubs.AuthStates
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

@Preview
@Composable
fun PreviewNewUser() {
    NxModsTheme {
        NxModsAuthScreen(
            model = AuthStates.newUserKeyValidating
        )
    }
}

@Preview
@Composable
fun PreviewNewUserDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsAuthScreen(
            model = AuthStates.newUserKeyValidating
        )
    }
}


@Preview
@Composable
fun PreviewNewUserValid() {
    NxModsTheme {
        NxModsAuthScreen(
            model = AuthStates.newUserKeyValid
        )
    }
}

@Preview
@Composable
fun PreviewNewUserValidDark() {
    NxModsTheme(useDarkTheme = true) {
        NxModsAuthScreen(
            model = AuthStates.newUserKeyValid
        )
    }
}
