package com.sedsoftware.nxmods.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

@Suppress("FunctionName")
fun NexusDatabaseDriver(): SqlDriver =
    NativeSqliteDriver(
        schema = NexusDatabase.Schema,
        name = "NexusDatabase.db"
    )
