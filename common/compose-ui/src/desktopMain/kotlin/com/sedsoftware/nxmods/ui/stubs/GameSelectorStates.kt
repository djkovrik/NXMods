package com.sedsoftware.nxmods.ui.stubs

import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.component.gameselector.model.GameInfoModel

internal object GameSelectorStates {

    private val default = NxModsGameSelector.Model(
        games = emptyList(),
        bookmarkedCounter = 0,
        progressVisible = false,
        nextButtonAvailable = false
    )

    private val defaultGame = GameInfoModel(
        id = 123,
        name = "Game",
        genre = "Action",
        mods = "",
        downloads = "",
        domain = "domain",
        bookmarked = false,
    )

    val loading = default.copy(progressVisible = true)

    private val dummyList = listOf(
        defaultGame.copy(
            id = 100,
            name = "Morrowind",
            genre = "RPG",
            domain = "morrowind",
            mods = "10.1k",
            downloads = "47.4M"
        ),
        defaultGame.copy(
            id = 100,
            name = "Oblivion",
            genre = "RPG",
            domain = "oblivion",
            mods = "30.9k",
            downloads = "263.1M",
            bookmarked = true
        ),
        defaultGame.copy(
            id = 333,
            name = "Cyberpunk 2077",
            genre = "Action",
            domain = "cyberpunk2077",
            mods = "5.4k",
            downloads = "103.6M"
        ),
    )

    val loaded = default.copy(
        games = dummyList + dummyList + dummyList + dummyList + dummyList + dummyList,
        nextButtonAvailable = true,
        bookmarkedCounter = 5
    )
}
