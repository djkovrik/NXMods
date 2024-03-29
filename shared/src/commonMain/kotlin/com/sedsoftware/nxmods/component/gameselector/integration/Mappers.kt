package com.sedsoftware.nxmods.component.gameselector.integration

import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector.Model
import com.sedsoftware.nxmods.component.gameselector.model.GameInfoModel
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.utils.prettify

internal val stateToModel: (State) -> Model = {
    Model(
        games = it.displayedGames.toModelList(),
        bookmarkedCounter = it.bookmarkedGamesCounter,
        searchQuery = it.searchQueryInput,
        searchVisible = it.searchFieldVisible,
        progressVisible = it.progressVisible,
        nextButtonAvailable = it.bookmarkedGamesCounter > 0,
        emptyPlaceholderVisible = it.searchQueryInput.isNotEmpty() && it.displayedGames.isEmpty()
    )
}

internal fun doMapping(info: GameInfo): GameInfoModel =
    GameInfoModel(
        id = info.id,
        name = info.name,
        genre = info.genre,
        mods = info.modsCount.prettify(),
        downloads = info.downloadsCount.prettify(),
        domain = info.domain,
        bookmarked = info.isBookmarked
    )

internal fun List<GameInfo>.toModelList(): List<GameInfoModel> = map(::doMapping)
