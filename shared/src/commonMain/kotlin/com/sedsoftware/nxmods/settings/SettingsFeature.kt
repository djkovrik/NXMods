package com.sedsoftware.nxmods.settings

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.settings.internal.NxSharedSettings

interface SettingsFeatureModule {
    val settings: NxModsSettings
}

interface SettingsFeatureComponent : SettingsFeatureModule

interface SettingsComponentDependencies {
    val settings: Settings
}

fun SettingsFeatureModule(dependencies: SettingsComponentDependencies): SettingsFeatureModule {
    return object : SettingsFeatureModule {
        override val settings: NxModsSettings by lazy { NxSharedSettings(dependencies.settings) }
    }
}

fun SettingsFeatureComponent(dependencies: SettingsComponentDependencies): SettingsFeatureComponent {
    val settingsModule = SettingsFeatureModule(dependencies)
    return object : SettingsFeatureComponent, SettingsFeatureModule by settingsModule {}
}

@Suppress("FunctionName")
fun SettingsFeatureComponentMock(): SettingsFeatureComponent {
    return object : SettingsFeatureComponent {
        override val settings: NxModsSettings by lazy { NxSharedSettings(MapSettings()) }
    }
}
