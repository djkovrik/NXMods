@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.sedsoftware.nxmods.ui

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.ui.component.ButtonMain
import com.sedsoftware.nxmods.ui.component.NxAppBar
import com.sedsoftware.nxmods.ui.component.ShapedSurface
import com.sedsoftware.nxmods.ui.component.SplashLogo
import dev.icerock.moko.resources.compose.stringResource

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
@Suppress("LongMethod")
internal fun NxModsAuthScreen(
    model: NxModsAuth.Model,
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit = {},
    onValidateClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {},
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    if (model.progressVisible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            SplashLogo(modifier = modifier.scale(scale))
        }
    } else {
        Scaffold(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            topBar = {
                NxAppBar {
                    Text(
                        text = stringResource(MR.strings.auth_header),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = modifier.padding(start = 32.dp, end = 32.dp, top = 16.dp)
                    )
                }
            }
        ) { paddingValues ->
            ShapedSurface(paddingValues = paddingValues) {
                Column(modifier = modifier.fillMaxWidth()) {
                    Box(modifier = modifier.weight(1f)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = modifier.fillMaxSize(),
                        ) {
                            Text(
                                text = stringResource(MR.strings.auth_api_key),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = modifier.padding(horizontal = 32.dp)
                            )

                            OutlinedTextField(
                                value = model.currentInput,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .padding(start = 32.dp, end = 32.dp, bottom = 16.dp, top = 16.dp),
                                onValueChange = onTextChanged,
                                readOnly = model.status == ApiKeyStatus.VALIDATION,
                                isError = model.status == ApiKeyStatus.INVALID,
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    autoCorrect = false
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() }
                                ),
                                shape = MaterialTheme.shapes.medium
                            )
                        }
                    }
                    Box(modifier = modifier.weight(1f)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxSize(),
                        ) {
                            Text(
                                text = stringResource(MR.strings.auth_api_key_desc),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = modifier.padding(horizontal = 64.dp),
                            )


                            Spacer(modifier = modifier.weight(1f))

                            when (model.status) {
                                ApiKeyStatus.VALIDATION -> {
                                    Text(
                                        text = stringResource(MR.strings.auth_api_key_validation),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }

                                ApiKeyStatus.VALID -> {
                                    Text(
                                        text = stringResource(MR.strings.auth_api_key_valid),
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }

                                ApiKeyStatus.INVALID -> {
                                    Text(
                                        text = stringResource(MR.strings.auth_api_key_invalid),
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }

                                else -> Unit
                            }

                            Row(
                                modifier = modifier.padding(all = 16.dp)
                            ) {
                                ButtonMain(
                                    onClick = onValidateClicked,
                                    enabled = model.validateButtonAvailable,
                                    modifier = modifier.padding(all = 8.dp)
                                ) {
                                    Text(
                                        text = stringResource(MR.strings.auth_button_validate),
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }

                                ButtonMain(
                                    onClick = onNextClicked,
                                    enabled = model.nextButtonAvailable,
                                    modifier = modifier.padding(all = 8.dp)
                                ) {
                                    Text(
                                        text = stringResource(MR.strings.auth_button_next),
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
