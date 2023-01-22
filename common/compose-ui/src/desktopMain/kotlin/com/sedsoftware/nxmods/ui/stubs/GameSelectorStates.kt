package com.sedsoftware.nxmods.ui.stubs

import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.domain.entity.GameInfo

object GameSelectorStates {

    private val default = NxModsGameSelector.Model(
        games = emptyList(),
        bookmarkedCounter = 0,
        progressVisible = false,
        nextButtonAvailable = false
    )

    private val defaultGame = GameInfo(
        id = 123,
        name = "Game",
        forumUrl = "http",
        nexusmodsUrl = "http",
        genre = "Action",
        filesCount = 12345L,
        downloadsCount = 123456L,
        domain = "domain",
        filesViews = 1234L,
        authors = 10L,
        fileEndorsements = 1234L,
        modsCount = 1111L,
        isBookmarked = false,
        categories = emptyList()
    )

    val loading = default.copy(progressVisible = true)

    val loaded = default.copy(
        games = listOf(
            defaultGame.copy(id = 1, name = "Skyrim", domain = "skyrim"),
            defaultGame.copy(id = 2, name = "Cyberpunk 2077", domain = "cyberpunk2077", isBookmarked = true),
            defaultGame.copy(id = 3, name = "Morrowind", domain = "morrowind")
        ),
        nextButtonAvailable = true
    )
}
