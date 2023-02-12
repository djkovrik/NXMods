@file:Suppress("MagicNumber")

package com.sedsoftware.nxmods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.sedsoftware.nxmods.database.DatabaseFeatureComponent
import com.sedsoftware.nxmods.network.NetworkFeatureComponent
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.integration.NxModsRootComponent
import com.sedsoftware.nxmods.settings.SettingsFeatureComponent
import com.sedsoftware.nxmods.ui.NxModsRootContent
import com.sedsoftware.nxmods.ui.theme.NxModsTheme

class MainActivity : ComponentActivity() {

    private val databaseFeature: DatabaseFeatureComponent
        get() = DatabaseScopedComponent.get(applicationContext)

    private val settingsFeature: SettingsFeatureComponent
        get() = SettingsScopedComponent.get(applicationContext)

    private val networkFeature: NetworkFeatureComponent
        get() = NetworkScopedComponent.get(settingsFeature)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root: NxModsRoot = todoRoot(defaultComponentContext())

        setContent {
            NxModsTheme {
                NxModsRootContent(root)
            }
        }
    }

    private fun todoRoot(componentContext: ComponentContext): NxModsRoot =
        NxModsRootComponent(
            componentContext = componentContext,
            storeFactory = DefaultStoreFactory(),
            nxModsApi = networkFeature.api,
            nxModsDatabase = databaseFeature.database,
            nxModsSettings = settingsFeature.settings
        )
}
