package com.sedsoftware.nxmods.database.serializer

import com.sedsoftware.nxmods.domain.entity.ModCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class ModCategorySerializable(
    @SerialName("category_id") val id: Long,
    @SerialName("name") val name: String
) {

    companion object {
        private val json = Json {
            isLenient = true
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

        fun List<ModCategory>.asString(): String {
            val serializable = map {
                ModCategorySerializable(
                    id = it.id,
                    name = it.name
                )
            }
            return json.encodeToString(serializable)
        }

        fun String.asModCategories(): List<ModCategory> {
            val deserialized: List<ModCategorySerializable> = json.decodeFromString(this)
            return deserialized.map {
                ModCategory(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }
}
