package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus

@Composable
fun NxModsAuthContent(component: NxModsAuth) {
    val model: NxModsAuth.Model by component.models.subscribeAsState()
    NxModsAuthScreen(
        model = model,
        onTextChanged = component::onTextEntered,
        onValidateClicked = component::onValidateButtonClicked,
        onNextClicked = component::onNextButtonClicked
    )
}

@Composable
fun NxModsAuthScreen(
    model: NxModsAuth.Model,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {},
    onValidateClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
    ) {
        TextField(
            value = model.currentInput,
            label = { Text(text = "Please enter your nexusmods API key:") },
            modifier = modifier.fillMaxWidth().padding(8.dp),
            onValueChange = onTextChanged,
            readOnly = model.progressVisible,
            isError = model.status == ApiKeyStatus.INVALID
        )

        Button(
            onClick = onValidateClicked,
            enabled = model.validateButtonAvailable,
        ) {
            Text(text = "Validate")
        }

        Button(
            onClick = onNextClicked,
            enabled = model.nextButtonAvailable,
        ) {
            Text(text = "Next")
        }

        Text(text = model.status.name)

        if (model.progressVisible) {
            CircularProgressIndicator(
                modifier = modifier
            )
        }
    }
}
