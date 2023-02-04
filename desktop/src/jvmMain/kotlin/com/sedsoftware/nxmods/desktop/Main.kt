@file:OptIn(ExperimentalDecomposeApi::class, ExperimentalCoroutinesApi::class)

package com.sedsoftware.nxmods.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.core.utils.setMainThreadId
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.badoo.reaktive.coroutinesinterop.asScheduler
import com.badoo.reaktive.scheduler.overrideSchedulers
import com.sedsoftware.nxmods.database.DatabaseFeatureComponent
import com.sedsoftware.nxmods.network.NetworkFeatureComponent
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.integration.NxModsRootComponent
import com.sedsoftware.nxmods.settings.SettingsFeatureComponent
import com.sedsoftware.nxmods.ui.NxModsRootContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.swing.SwingUtilities

fun main() {
    overrideSchedulers(main = Dispatchers.Main::asScheduler)

    val lifecycle = LifecycleRegistry()
    lateinit var root: NxModsRoot

    SwingUtilities.invokeAndWait {
        setMainThreadId(Thread.currentThread().id)
        root = nxModsRoot(DefaultComponentContext(lifecycle = lifecycle))
    }

    application {
        val windowState = rememberWindowState()
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "NxMods"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    NxModsRootContent(root)
                }
            }
        }
    }
}

private fun nxModsRoot(componentContext: ComponentContext): NxModsRoot {
    val databaseFeature: DatabaseFeatureComponent = DatabaseScopedComponent.get()
    val settingsFeature: SettingsFeatureComponent = SettingsScopedComponent.get()
    val networkFeature: NetworkFeatureComponent = NetworkScopedComponent.get(settingsFeature)

    return NxModsRootComponent(
        componentContext = componentContext,
        storeFactory = DefaultStoreFactory(),
        nxModsApi = networkFeature.api,
        nxModsDatabase = databaseFeature.database,
        nxModsSettings = settingsFeature.settings
    )
}
