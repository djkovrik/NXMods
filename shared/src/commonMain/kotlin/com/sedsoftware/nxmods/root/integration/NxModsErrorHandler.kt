package com.sedsoftware.nxmods.root.integration

import com.badoo.reaktive.base.ValueCallback
import com.sedsoftware.nxmods.domain.exception.BookmarkGameException
import com.sedsoftware.nxmods.domain.exception.FetchLocalGameListException
import com.sedsoftware.nxmods.domain.exception.FetchRemoteGameListException
import com.sedsoftware.nxmods.domain.exception.LoadModsListException
import com.sedsoftware.nxmods.domain.exception.ModEndorseException
import com.sedsoftware.nxmods.domain.exception.ModInfoLoadingException
import com.sedsoftware.nxmods.domain.exception.ModTrackException
import com.sedsoftware.nxmods.domain.exception.PreferencesChangeException
import com.sedsoftware.nxmods.domain.exception.SwitchSelectedGameException
import com.sedsoftware.nxmods.domain.exception.ValidateApiKeyException

internal class NxModsErrorHandler {

    // TODO Localize messages
    fun consume(throwable: Throwable, observable: ValueCallback<String>) {
        observable.onNext(
            when (throwable) {
                is ValidateApiKeyException -> "Failed to validate API key"
                is FetchRemoteGameListException -> "Failed to fetch games list"
                is FetchLocalGameListException -> "Failed to load cached games list"
                is BookmarkGameException -> "Failed to bookmark game"
                is LoadModsListException -> "Failed to load mods list"
                is SwitchSelectedGameException -> "Failed to change tracked game"
                is PreferencesChangeException -> "Failed to change preferences"
                is ModInfoLoadingException -> "Failed to load mod info"
                is ModEndorseException -> "Failed to endorse mod"
                is ModTrackException -> "Failed to track mod"
                else -> "Unknown error"
            }
        )
    }
}
