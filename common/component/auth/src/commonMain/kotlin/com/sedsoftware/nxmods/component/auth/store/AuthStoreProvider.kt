@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.auth.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.completable.doOnAfterError
import com.badoo.reaktive.completable.doOnBeforeSubscribe
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Intent
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Label
import com.sedsoftware.nxmods.component.auth.store.AuthStore.State
import com.sedsoftware.nxmods.domain.ApiKeyManager

internal class AuthStoreProvider(
    private val storeFactory: StoreFactory,
    private val manager: ApiKeyManager
) {

    fun create(): AuthStore =
        object : AuthStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "AuthStore",
            initialState = State(),
            bootstrapper = reaktiveBootstrapper {
                manager.getCurrentApiKey()
                    .observeOn(mainScheduler)
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
                        .observeOn(mainScheduler)
                        .doOnBeforeSubscribe { dispatch(Msg.ValidationRequested) }
                        .doOnAfterError { throwable ->
                            dispatch(Msg.ValidationFailed)
                            publish(Label.ErrorCaught(throwable))
                        }
                        .subscribeScoped {
                            dispatch(Msg.ValidationCompleted)
                            publish(Label.ExistingUserValidationCompleted)
                        }
                }

                onIntent<Intent.InputText> {
                    dispatch(Msg.UserTextEntered(it.text))
                }

                onIntent<Intent.ClickValidateButton> {
                    manager.validateApiKey(key = state.textInput)
                        .observeOn(mainScheduler)
                        .doOnBeforeSubscribe { dispatch(Msg.ValidationRequested) }
                        .doOnAfterError { throwable ->
                            dispatch(Msg.ValidationFailed)
                            publish(Label.ErrorCaught(throwable))
                        }
                        .subscribeScoped {
                            dispatch(Msg.ValidationCompleted)
                        }
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
                    is Msg.ValidationCompleted -> copy(
                        progressVisible = false,
                        apiKeyStatus = ApiKeyStatus.VALID
                    )
                    is Msg.ValidationFailed -> copy(
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
        object ValidationCompleted : Msg
        object ValidationFailed : Msg
        object NewUserDetected : Msg
    }
}
