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
import com.sedsoftware.nxmods.MainRes
import io.github.skeptick.libres.compose.painterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
fun SplashLogo(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(128.dp)
    ) {
        Image(
            painter = MainRes.image.nexus_logo.painterResource(),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = modifier.padding(all = 32.dp)
        )
    }
}
