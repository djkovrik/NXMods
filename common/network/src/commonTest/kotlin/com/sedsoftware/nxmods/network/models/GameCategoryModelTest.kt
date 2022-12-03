package com.sedsoftware.nxmods.network.models

import com.sedsoftware.nxmods.network.stubs.Utils
import kotlin.test.Test
import kotlin.test.assertTrue

class GameCategoryModelTest {

    @Test
    fun gameCategorySerialization_test() {
        val json = """
        {
            "category_id": 123,
            "name": "Cyberpunk 2077",
            "parent_category": false
        }
        """.trimIndent()

        val category: GameCategoryModel = Utils.decodeFromJson(json)

        assertTrue(category.name == "Cyberpunk 2077", "Should have deserialized name")
        assertTrue(category.id == 123L, "Should have deserialized id")
    }
}
