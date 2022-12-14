package com.sedsoftware.nxmods.database.mappers

import com.sedsoftware.nxmods.database.GameInfoEntity
import com.sedsoftware.nxmods.database.serializer.GameCategorySerializable.Companion.asGameCategories
import com.sedsoftware.nxmods.database.serializer.GameCategorySerializable.Companion.asString
import com.sedsoftware.nxmods.domain.entity.GameInfo

internal object GameInfoEntityMappers {
    val gameInfoListToDomain: List<GameInfoEntity>.() -> List<GameInfo> = {
        map { gameInfoToDomain(it) }
    }

    val gameInfoToDomain: GameInfoEntity.() -> GameInfo = {
        GameInfo(
            id = id,
            name = name,
            forumUrl = forumUrl,
            nexusmodsUrl = nexusmodsUrl,
            genre = genre,
            filesCount = fileCount,
            downloadsCount = downloadsCount,
            domain = domain,
            filesViews = fileViews,
            authors = authors,
            fileEndorsements = fileEndorsements,
            modsCount = modsCount,
            isBookmarked = bookmarked == 1L,
            categories = categories?.asGameCategories().orEmpty()
        )
    }

    val gameInfoToDb: GameInfo.() -> GameInfoEntity = {
        GameInfoEntity(
            id = id,
            name = name,
            bookmarked = if (isBookmarked) 1L else 0L,
            forumUrl = forumUrl,
            nexusmodsUrl = nexusmodsUrl,
            genre = genre,
            fileCount = filesCount,
            downloadsCount = downloadsCount,
            domain = domain,
            fileViews = filesViews,
            authors = authors,
            fileEndorsements = fileEndorsements,
            modsCount = modsCount,
            categories = categories.asString()
        )
    }
}
