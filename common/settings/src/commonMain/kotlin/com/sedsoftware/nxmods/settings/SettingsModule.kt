package com.sedsoftware.nxmods.settings

import com.russhwolf.settings.Settings
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.settings.internal.NxSharedSettings

interface SettingsModule {
    val settings: NxModsSettings
}

interface SettingsModuleDependencies {
    val baseSettings: Settings
}

fun SettingsModule(dependencies: SettingsModuleDependencies): SettingsModule {
    return object : SettingsModule {
        override val settings: NxModsSettings by lazy { NxSharedSettings(dependencies.baseSettings) }
    }
}
