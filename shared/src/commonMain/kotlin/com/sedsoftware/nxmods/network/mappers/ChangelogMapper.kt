package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.ChangelogItem

internal object ChangelogMapper {

    val changelogToDomain: Map<String, List<String>>.() -> List<ChangelogItem> = {
        map { (key, value) ->
            ChangelogItem(
                version = key,
                changes = value
            )
        }
    }
}
