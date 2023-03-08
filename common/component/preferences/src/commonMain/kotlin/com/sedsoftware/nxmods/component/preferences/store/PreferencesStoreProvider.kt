@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.preferences.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.sedsoftware.nxmods.component.preferences.domain.NxModsPreferenceManager
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferences
import com.sedsoftware.nxmods.component.preferences.dsl.updateBoolean
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.Intent
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.Label
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.State
import com.sedsoftware.nxmods.domain.exception.PreferencesChangeException

internal class PreferencesStoreProvider(
    private val storeFactory: StoreFactory,
    private val manager: NxModsPreferenceManager,
    private val observeScheduler: Scheduler = mainScheduler
) {

    fun create(autoInit: Boolean = true): PreferencesStore =
        object : PreferencesStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "PreferencesStore",
            initialState = State(),
            autoInit = autoInit,
            bootstrapper = SimpleBootstrapper<Action>(Action.BuildPreferenceScreen),
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.BuildPreferenceScreen> {
                    manager.buildPreferenceScreen()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onSuccess = { settings ->
                                dispatch(Msg.PreferenceScreenReady(settings))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(PreferencesChangeException(throwable)))
                            }
                        )
                }

                onIntent<Intent.ChangeSwitch> {
                    manager.changePreference(it.key, it.value)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onComplete = {
                                dispatch(Msg.SwitcherUpdated(it.key, it.value))
                                publish(Label.PreferencesChanged)
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(PreferencesChangeException(throwable)))
                            }
                        )
                }

                onIntent<Intent.ClickButton> {
                    when (it.key) {
                        NxPreferenceKeyUnique.MANAGE_GAMES -> publish(Label.GameSelectorRequested)
                        else -> Unit
                    }
                }

                onIntent<Intent.CloseScreen> { publish(Label.ScreenClosed) }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.PreferenceScreenReady -> copy(
                        preferences = msg.preferences
                    )

                    is Msg.SwitcherUpdated -> copy(
                        preferences = preferences.updateBoolean(msg.key, msg.value)
                    )
                }
            }
        ) {}

    private sealed interface Action {
        object BuildPreferenceScreen : Action
    }

    private sealed interface Msg {
        data class PreferenceScreenReady(val preferences: NxPreferences) : Msg
        data class SwitcherUpdated(val key: NxPreferenceKeyUnique, val value: Boolean) : Msg
    }
}
