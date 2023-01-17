@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.auth.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.observable.doOnBeforeSubscribe
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.sedsoftware.nxmods.component.auth.domain.NxModsAuthManager
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Intent
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Label
import com.sedsoftware.nxmods.component.auth.store.AuthStore.State
import com.sedsoftware.nxmods.domain.exception.ValidateApiKeyException

internal class AuthStoreProvider(
    private val storeFactory: StoreFactory,
    private val manager: NxModsAuthManager,
    private val observeScheduler: Scheduler = mainScheduler
) {

    fun create(autoInit: Boolean = true): AuthStore =
        object : AuthStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "AuthStore",
            initialState = State(),
            autoInit = autoInit,
            bootstrapper = reaktiveBootstrapper {
                manager.getCurrentApiKey()
                    .observeOn(observeScheduler)
                    .subscribeScoped { apiKey: String ->
                        if (apiKey.isEmpty()) {
                            dispatch(Action.HandleNewUser)
                        } else {
                            dispatch(Action.HandleExistingUser(apiKey))
                        }
                    }
            },
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.HandleNewUser> {
                    dispatch(Msg.NewUserDetected)
                }

                onAction<Action.HandleExistingUser> {
                    manager.validateApiKey(key = it.key)
                        .observeOn(observeScheduler)
                        .doOnBeforeSubscribe { dispatch(Msg.ValidationRequested) }
                        .subscribeScoped(
                            onNext = { key ->
                                dispatch(Msg.ValidationRequestCompleted(key))
                                if (key.isNotEmpty()) {
                                    publish(Label.ExistingUserValidationCompleted)
                                }
                            },
                            onError = { throwable ->
                                dispatch(Msg.ValidationRequestFailed)
                                publish(Label.ErrorCaught(ValidateApiKeyException(throwable)))
                            }
                        )
                }

                onIntent<Intent.InputText> {
                    dispatch(Msg.UserTextEntered(it.text))
                }

                onIntent<Intent.ClickValidateButton> {
                    manager.validateApiKey(key = state.textInput)
                        .observeOn(observeScheduler)
                        .doOnBeforeSubscribe { dispatch(Msg.ValidationRequested) }
                        .subscribeScoped(
                            onNext = { key ->
                                dispatch(Msg.ValidationRequestCompleted(key))
                            },
                            onError = { throwable ->
                                dispatch(Msg.ValidationRequestFailed)
                                publish(Label.ErrorCaught(ValidateApiKeyException(throwable)))
                            }
                        )
                }

                onIntent<Intent.ClickNextButton> {
                    publish(Label.NewUserValidationCompleted)
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.UserTextEntered -> copy(
                        textInput = msg.text
                    )

                    is Msg.NewUserDetected -> copy(
                        progressVisible = false,
                        apiKeyStatus = ApiKeyStatus.NOT_FOUND
                    )

                    is Msg.ValidationRequested -> copy(
                        progressVisible = true
                    )

                    is Msg.ValidationRequestCompleted -> copy(
                        progressVisible = false,
                        apiKeyStatus = if (msg.key.isNotEmpty()) {
                            ApiKeyStatus.VALID
                        } else {
                            ApiKeyStatus.INVALID
                        }
                    )

                    is Msg.ValidationRequestFailed -> copy(
                        progressVisible = false,
                        apiKeyStatus = ApiKeyStatus.INVALID
                    )
                }
            }
        ) {}

    private sealed interface Action {
        data class HandleExistingUser(val key: String) : Action
        object HandleNewUser : Action
    }

    private sealed interface Msg {
        data class UserTextEntered(val text: String) : Msg
        object ValidationRequested : Msg
        data class ValidationRequestCompleted(val key: String) : Msg
        object ValidationRequestFailed : Msg
        object NewUserDetected : Msg
    }
}
