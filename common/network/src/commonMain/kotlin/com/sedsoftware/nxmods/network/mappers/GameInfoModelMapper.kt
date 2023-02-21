package com.sedsoftware.nxmods.network.mappers

import com.sedsoftware.nxmods.domain.entity.ModCategory
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.network.models.GameCategoryModel
import com.sedsoftware.nxmods.network.models.GameInfoModel

internal object GameInfoModelMapper {

    val gameInfoModelToDomain: GameInfoModel.() -> GameInfo = {
        GameInfo(
            id = id,
            name = name,
            forumUrl = forumUrl,
            nexusmodsUrl = nexusmodsUrl,
            genre = genre,
            filesCount = filesCount,
            downloadsCount = downloadsCount,
            domain = domain,
            filesViews = filesViews,
            authors = authors,
            fileEndorsements = fileEndorsements,
            modsCount = modsCount,
            isBookmarked = false,
            categories = categories.map(::toDomain)
        )
    }

    val gameInfoModelListToDomain: List<GameInfoModel>.() -> List<GameInfo> = {
        map { gameInfoModelToDomain(it) }
    }

    private fun toDomain(from: GameCategoryModel): ModCategory =
        ModCategory(
            id = from.id,
            name = from.name
        )
}
