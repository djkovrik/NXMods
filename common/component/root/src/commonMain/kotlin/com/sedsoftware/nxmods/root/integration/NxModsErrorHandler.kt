package com.sedsoftware.nxmods.root.integration

import com.badoo.reaktive.base.ValueCallback
import com.sedsoftware.nxmods.domain.exception.ValidateApiKeyException

internal class NxModsErrorHandler {

    // TODO Localize messages
    fun consume(throwable: Throwable, observable: ValueCallback<String>) {
        observable.onNext(
            when (throwable) {
                is ValidateApiKeyException -> "Failed to validate API key"
                else -> "Unknown error"
            }
        )
    }
}
