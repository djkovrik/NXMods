package com.sedsoftware.nxmods.desktop

import com.russhwolf.settings.Settings
import com.sedsoftware.nxmods.database.DatabaseComponentDependencies
import com.sedsoftware.nxmods.database.DatabaseFeatureComponent
import com.sedsoftware.nxmods.database.NexusDatabaseDriver
import com.sedsoftware.nxmods.network.NetworkFeatureComponent
import com.sedsoftware.nxmods.settings.SettingsComponentDependencies
import com.sedsoftware.nxmods.settings.SettingsFeatureComponent
import com.sedsoftware.nxmods.settings.SettingsFeatureModule
import com.sedsoftware.nxmods.settings.SharedSettingsFactory
import com.squareup.sqldelight.db.SqlDriver

open class ScopedComponent<T : Any> {

    protected var component: T? = null

    fun destroy() {
        component = null
    }
}

object DatabaseScopedComponent : ScopedComponent<DatabaseFeatureComponent>() {

    fun get(): DatabaseFeatureComponent {
        if (component == null) {
            component = DatabaseFeatureComponent(
                dependencies = object : DatabaseComponentDependencies {
                    override val driver: SqlDriver = NexusDatabaseDriver()
                }
            )
        }

        return component ?: error("DatabaseFeatureComponent not initialized")
    }
}

object SettingsScopedComponent : ScopedComponent<SettingsFeatureComponent>() {

    fun get(): SettingsFeatureComponent {
        if (component == null) {
            component = SettingsFeatureComponent(
                dependencies = object : SettingsComponentDependencies {
                    override val settings: Settings = SharedSettingsFactory()
                }
            )
        }

        return component ?: error("SettingsFeatureComponent not initialized")
    }
}

object NetworkScopedComponent : ScopedComponent<NetworkFeatureComponent>() {

    fun get(module: SettingsFeatureModule): NetworkFeatureComponent {
        if (component == null) {
            component = NetworkFeatureComponent(module)
        }

        return component ?: error("NetworkFeatureComponent not initialized")
    }
}
