package com.sedsoftware.nxmods.component.modinfo.integration

import com.sedsoftware.nxmods.component.modinfo.NxModsInfo.Model
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.State
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.utils.format
import com.sedsoftware.nxmods.utils.prettify

internal val stateToModel: (State) -> Model = {
    Model(
        name = it.name,
        summary = it.summary,
        version = it.version,
        picture = it.pictureUrl,
        category = it.categoryName,
        downloads = it.downloads.prettify(),
        endorsements = it.endorsements.prettify(),
        createdTime = it.createdTime.format(),
        updatedTime = it.updatedTime.format(),
        author = it.author,
        endorsed = it.isTracked,
        tracked = it.isEndorsed,
        progressVisible = it.loadingInProgress
    )
}

internal fun State.applyModInfo(info: ModInfo): State =
    copy(
        name = info.name,
        summary = info.summary,
        version = info.version,
        pictureUrl = info.pictureUrl,
        categoryName = info.categoryName,
        downloads = info.modDownloadsUnique,
        endorsements = info.endorsementCount,
        createdTime = info.createdTime,
        updatedTime = info.updatedTime,
        author = info.author,
        isTracked = info.isTracked,
        isEndorsed = info.isEndorsed,
        loadingInProgress = false
    )
