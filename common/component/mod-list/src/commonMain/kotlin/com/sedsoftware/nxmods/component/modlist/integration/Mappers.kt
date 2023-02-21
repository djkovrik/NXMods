package com.sedsoftware.nxmods.component.modlist.integration

import com.sedsoftware.nxmods.component.modlist.NxModsList.Model
import com.sedsoftware.nxmods.component.modlist.model.ModInfoModel
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.utils.asThumbnail
import com.sedsoftware.nxmods.utils.prettify

internal val stateToModel: (State) -> Model = {
    Model(
        mods = it.mods.toModelList(it.activeGame),
        progressVisible = it.progress,
        emptyListPlaceholderVisible = !it.progress && it.mods.isEmpty()
    )
}

internal fun doMapping(info: ModInfo, gameInfo: GameInfo?): ModInfoModel {
    val category = gameInfo?.categories?.firstOrNull { it.id == info.categoryId }

    return ModInfoModel(
        modId = info.modId,
        gameId = info.gameId,
        picture = info.pictureUrl.asThumbnail(),
        domain = info.domainName,
        name = info.name,
        summary = info.summary,
        author = info.author,
        category = category?.name.orEmpty(),
        downloads = info.modDownloads.prettify(),
        endorsements = info.endorsementCount.prettify(),
    )
}

internal fun List<ModInfo>.toModelList(gameInfo: GameInfo?): List<ModInfoModel> =
    filter { it.available }.map { doMapping(it, gameInfo) }
