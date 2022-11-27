package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.network.mappers.ChangelogMapper.changelogToDomain
import com.sedsoftware.nxmods.network.stubs.responses.GetChangelog
import kotlin.test.Test
import kotlin.test.assertTrue

class ChangelogMapperTest {

    @Test
    fun changelogMapping_test() {
        val response: Map<String, List<String>> = GetChangelog.response
        val mapped: List<ChangelogItem> = changelogToDomain(response)

        mapped.forEach { item ->
            assertTrue(item.version.isNotEmpty(), "Should have mapped version")
            assertTrue(item.changes.isNotEmpty(), "Should have mapped list of changes")
        }
    }
}
