package com.sedsoftware.nxmods.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.russhwolf.settings.Settings
import com.sedsoftware.nxmods.database.DatabaseComponentDependencies
import com.sedsoftware.nxmods.database.DatabaseFeatureComponent
import com.sedsoftware.nxmods.database.NexusDatabaseDriver
import com.sedsoftware.nxmods.network.NetworkFeatureComponent
import com.sedsoftware.nxmods.root.integration.NxModsRootComponent
import com.sedsoftware.nxmods.settings.SettingsComponentDependencies
import com.sedsoftware.nxmods.settings.SettingsFeatureComponent
import com.sedsoftware.nxmods.settings.SharedSettingsFactory
import com.squareup.sqldelight.db.SqlDriver

@Suppress("FunctionName")
fun NxModsRootFactory(componentContext: ComponentContext): NxModsRoot {

    val databaseFeature = DatabaseFeatureComponent(
        dependencies = object : DatabaseComponentDependencies {
            override val driver: SqlDriver = NexusDatabaseDriver()
        }
    )

    val settingsFeature = SettingsFeatureComponent(
        dependencies = object : SettingsComponentDependencies {
            override val settings: Settings = SharedSettingsFactory()
        }
    )

    val networkFeature = NetworkFeatureComponent(settingsFeature)

    return NxModsRootComponent(
        componentContext = componentContext,
        storeFactory = DefaultStoreFactory(),
        nxModsApi = networkFeature.api,
        nxModsDatabase = databaseFeature.database,
        nxModsSettings = settingsFeature.settings
    )
}
