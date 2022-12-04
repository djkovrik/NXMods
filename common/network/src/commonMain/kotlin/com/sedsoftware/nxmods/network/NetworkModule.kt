package com.sedsoftware.nxmods.network

import com.sedsoftware.nxmods.domain.framework.CompletableSubject
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.network.internal.NxModsSharedApi
import com.sedsoftware.nxmods.network.internal.NxModsTestApi

interface NetworkModule {
    val api: NxModsApi
    val testApi: NxModsApi
}

interface NetworkModuleDependencies {
    val settings: NxModsSettings
    val testSubjects: NetworkModuleTestSubjects?
}

interface NetworkModuleTestSubjects {
    val endorse: CompletableSubject
    val track: CompletableSubject
}

fun NetworkModule(dependencies: NetworkModuleDependencies): NetworkModule {
    return object : NetworkModule {
        override val api: NxModsApi by lazy { NxModsSharedApi(dependencies.settings) }
        override val testApi: NxModsApi by lazy {
            val endorseSubject = dependencies.testSubjects?.endorse ?: CompletableSubject()
            val trackSubject = dependencies.testSubjects?.track ?: CompletableSubject()
            NxModsTestApi(endorseSubject, trackSubject)
        }
    }
}
