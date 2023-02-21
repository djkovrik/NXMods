package com.sedsoftware.nxmods.database.serialization

import com.sedsoftware.nxmods.database.serializer.ModCategorySerializable.Companion.asModCategories
import com.sedsoftware.nxmods.database.serializer.ModCategorySerializable.Companion.asString
import com.sedsoftware.nxmods.domain.entity.ModCategory
import kotlin.test.Test
import kotlin.test.assertTrue

class GameCategorySerializableTest {

    private val testCategories: List<ModCategory> = listOf(
        ModCategory(1, "Morrowind"),
        ModCategory(2, "Buildings"),
        ModCategory(3, "Dungeons and Locations"),
        ModCategory(4, "Gameplay")
    )

    private val testString: String = """ [
        {"category_id":1,"name":"Morrowind"},
        {"category_id":2,"name":"Buildings"},
        {"category_id":3,"name":"Dungeons and Locations"},
        {"category_id":4,"name":"Gameplay"}
   ]
    """.trimIndent()

    @Test
    fun asString_test() {
        val str = testCategories.asString()
        assertTrue(str.isNotEmpty(), "Serialized string should not be empty")
        assertTrue(str.contains("Morrowind"), "Serialized should contain category")
        assertTrue(str.contains("Buildings"), "Serialized should contain category")
        assertTrue(str.contains("Dungeons"), "Serialized should contain category")
        assertTrue(str.contains("Gameplay"), "Serialized should contain category")
    }

    @Test
    fun asGameCategories_test() {
        val deserialized = testString.asModCategories()
        assertTrue(deserialized.isNotEmpty(), "Deserialized list should not be empty")
        assertTrue(deserialized[0].id == 1L, "Deserialized item should have id")
        assertTrue(deserialized[1].id == 2L, "Deserialized item should have id")
        assertTrue(deserialized[2].id == 3L, "Deserialized item should have id")
        assertTrue(deserialized[3].id == 4L, "Deserialized item should have id")
        assertTrue(deserialized[0].name == "Morrowind", "Deserialized item should have name")
        assertTrue(deserialized[1].name == "Buildings", "Deserialized item should have name")
        assertTrue(deserialized[2].name == "Dungeons and Locations", "Deserialized item should have name")
        assertTrue(deserialized[3].name == "Gameplay", "Deserialized item should have name")
    }
}
