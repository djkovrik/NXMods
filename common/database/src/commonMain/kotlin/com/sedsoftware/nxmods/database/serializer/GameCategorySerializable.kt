package com.sedsoftware.nxmods.database.serializer

import com.sedsoftware.nxmods.domain.entity.GameCategory
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
internal class GameCategorySerializable(
    @SerialName("category_id") val id: Long,
    @SerialName("name") val name: String
) {

    companion object {
        private val json = Json {
            isLenient = true
            coerceInputValues = true
            ignoreUnknownKeys = true
        }

        fun List<GameCategory>.asString(): String {
            val serializable = map {
                GameCategorySerializable(
                    id = it.id,
                    name = it.name
                )
            }
            return json.encodeToString(serializable)
        }

        fun String.asGameCategories(): List<GameCategory> {
            val deserialized: List<GameCategorySerializable> = json.decodeFromString(this)
            return deserialized.map {
                GameCategory(
                    id = it.id,
                    name = it.name
                )
            }
        }
    }
}
