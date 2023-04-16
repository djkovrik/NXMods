@file:OptIn(ExperimentalResourceApi::class)

package com.sedsoftware.nxmods.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashLogo(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(128.dp)
    ) {
        Image(
            painter = painterResource("nexus_logo.png"),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = modifier.padding(all = 32.dp)
        )
    }
}
