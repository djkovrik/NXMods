package com.sedsoftware.nxmods.root.integration

import com.badoo.reaktive.base.ValueCallback
import com.sedsoftware.nxmods.domain.exception.BookmarkGameException
import com.sedsoftware.nxmods.domain.exception.FetchLocalGameListException
import com.sedsoftware.nxmods.domain.exception.FetchRemoteGameListException
import com.sedsoftware.nxmods.domain.exception.LoadModsListException
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
                else -> "Unknown error"
            }
        )
    }
}
