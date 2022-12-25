package com.sedsoftware.nxmods

import com.russhwolf.settings.MapSettings
import com.russhwolf.settings.Settings
import com.sedsoftware.nxmods.database.DatabaseModule
import com.sedsoftware.nxmods.domain.framework.CompletableSubject
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.network.NetworkModule
import com.sedsoftware.nxmods.network.NetworkModuleDependencies
import com.sedsoftware.nxmods.network.NetworkModuleTestSubjects
import com.sedsoftware.nxmods.settings.SettingsModule
import com.sedsoftware.nxmods.settings.SettingsModuleDependencies

internal object Stubs {

    val endorseSubject: CompletableSubject = CompletableSubject()

    val trackSubject: CompletableSubject = CompletableSubject()

    val api: NxModsApi
        get() = NetworkModule(
            dependencies = object : NetworkModuleDependencies {

                override val settings: NxModsSettings = Stubs.settings

                override val testSubjects: NetworkModuleTestSubjects =
                    object : NetworkModuleTestSubjects {
                        override val endorse: CompletableSubject = endorseSubject
                        override val track: CompletableSubject = trackSubject
                    }
            }
        ).testApi

    val settings: NxModsSettings
        get() = SettingsModule(
            dependencies = object : SettingsModuleDependencies {
                override val baseSettings: Settings = MapSettings()
            }
        ).settings

    val database: NxModsDatabase
        get() = DatabaseModule().testDatabase
}
