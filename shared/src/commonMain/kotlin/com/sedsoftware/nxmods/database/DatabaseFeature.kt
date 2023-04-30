package com.sedsoftware.nxmods.database

import com.sedsoftware.nxmods.database.internal.NxModsSharedDatabase
import com.sedsoftware.nxmods.database.internal.NxModsTestDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.squareup.sqldelight.db.SqlDriver

interface DatabaseFeatureModule {
    val database: NxModsDatabase
}

interface DatabaseFeatureComponent : DatabaseFeatureModule

interface DatabaseComponentDependencies {
    val driver: SqlDriver
}

fun DatabaseFeatureModule(dependencies: DatabaseComponentDependencies): DatabaseFeatureModule {
    return object : DatabaseFeatureModule {
        override val database: NxModsDatabase by lazy {
            val driver = dependencies.driver
            NxModsSharedDatabase(driver)
        }
    }
}

fun DatabaseFeatureComponent(dependencies: DatabaseComponentDependencies): DatabaseFeatureComponent {
    val databaseModule = DatabaseFeatureModule(dependencies)
    return object : DatabaseFeatureComponent, DatabaseFeatureModule by databaseModule {}
}

@Suppress("FunctionName")
fun DatabaseFeatureComponentMock(): DatabaseFeatureComponent {
    return object : DatabaseFeatureComponent {
        override val database: NxModsDatabase by lazy {
            NxModsTestDatabase()
        }
    }
}
