@file:Suppress("MagicNumber")

package com.sedsoftware.nxmods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.defaultComponentContext
import com.sedsoftware.nxmods.utils.ImageLoaderFactory
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.NxModsRootFactory
import com.sedsoftware.nxmods.ui.NxModsRootContent
import com.sedsoftware.nxmods.ui.theme.NxModsTheme
import com.seiko.imageloader.LocalImageLoader

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root: NxModsRoot = NxModsRootFactory(defaultComponentContext(), applicationContext)

        setContent {
            NxModsTheme {
                CompositionLocalProvider(
                    LocalImageLoader provides ImageLoaderFactory(applicationContext),
                ) {
                    NxModsRootContent(root)
                }
            }
        }
    }
}
