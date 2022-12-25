package com.sedsoftware.nxmods.database

import com.sedsoftware.nxmods.database.internal.NxModsSharedDatabase
import com.sedsoftware.nxmods.database.internal.NxModsTestDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.squareup.sqldelight.db.SqlDriver

interface DatabaseModule {
    val database: NxModsDatabase
    val testDatabase: NxModsDatabase
}

interface DatabaseModuleDependencies {
    val driver: SqlDriver
}

fun DatabaseModule(dependencies: DatabaseModuleDependencies? = null): DatabaseModule {
    return object : DatabaseModule {
        override val database: NxModsDatabase by lazy {
            val driver = dependencies?.driver ?: error("Database driver not provided")
            NxModsSharedDatabase(driver)
        }

        override val testDatabase: NxModsDatabase by lazy {
            NxModsTestDatabase()
        }
    }
}
