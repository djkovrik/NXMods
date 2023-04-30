package com.sedsoftware.nxmods.network

import com.sedsoftware.nxmods.domain.framework.CompletableSubject
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.network.internal.NxModsSharedApi
import com.sedsoftware.nxmods.network.internal.NxModsTestApi
import com.sedsoftware.nxmods.settings.SettingsFeatureModule

interface NetworkFeatureModule {
    val api: NxModsApi
}

interface NetworkFeatureComponent: NetworkFeatureModule

interface NetworkComponentTestDependencies {
    val endorse: CompletableSubject
    val track: CompletableSubject
}

fun NetworkFeatureModule(dependencies: SettingsFeatureModule): NetworkFeatureModule {
    return object : NetworkFeatureModule {
        override val api: NxModsApi by lazy { NxModsSharedApi(dependencies.settings) }
    }
}

fun NetworkFeatureComponent(dependencies: SettingsFeatureModule): NetworkFeatureComponent {
    val networkModule = NetworkFeatureModule(dependencies)
    return object : NetworkFeatureComponent, NetworkFeatureModule by networkModule {}
}

@Suppress("FunctionName")
fun NetworkFeatureComponentMock(dependencies: NetworkComponentTestDependencies): NetworkFeatureComponent {
    return object : NetworkFeatureComponent {
        override val api: NxModsApi by lazy {
            val endorseSubject = dependencies.endorse
            val trackSubject = dependencies.track
            NxModsTestApi(endorseSubject, trackSubject)
        }
    }
}
