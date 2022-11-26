package com.sedsoftware.nxmods.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

@Suppress("FunctionName")
fun NexusDatabaseDriver(context: Context): SqlDriver =
    AndroidSqliteDriver(
        schema = NexusDatabase.Schema,
        context = context,
        name = "NexusDatabase.db"
    )
