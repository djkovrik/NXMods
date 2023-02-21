@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.modlist.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.observable.doOnBeforeSubscribe
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.modlist.domain.NxModsListsManager
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.Intent
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.Label
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.exception.LoadModsListException
import com.sedsoftware.nxmods.domain.type.ModListType

internal class ModsListStoreProvider(
    private val storeFactory: StoreFactory,
    private val listType: ModListType,
    private val manager: NxModsListsManager,
    private val observeScheduler: Scheduler = mainScheduler
) {

    fun create(autoInit: Boolean = true): ModsListStore =
        object : ModsListStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "ModsListStore${listType.index}",
            initialState = State(),
            autoInit = autoInit,
            bootstrapper = reaktiveBootstrapper {
                dispatch(Action.LoadGame)
                dispatch(Action.LoadMods)
            },
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.LoadGame> {
                    manager.getActiveGameInfo()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onNext = { game ->
                                dispatch(Msg.ActiveGameLoaded(game))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(LoadModsListException(throwable)))
                            }
                        )
                }

                onAction<Action.LoadMods> {
                    manager.getModsList(listType)
                        .observeOn(observeScheduler)
                        .doOnBeforeSubscribe { dispatch(Msg.ModsLoadingStarted) }
                        .subscribeScoped(
                            onNext = { mods ->
                                dispatch(Msg.ModsLoadingCompleted(mods))
                            },
                            onError = { throwable ->
                                dispatch(Msg.ModsLoadingFailed)
                                publish(Label.ErrorCaught(LoadModsListException(throwable)))
                            }
                        )
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.ActiveGameLoaded -> copy(
                        activeGame = msg.game
                    )

                    is Msg.ModsLoadingCompleted -> copy(
                        progress = false,
                        mods = msg.mods
                    )

                    is Msg.ModsLoadingStarted -> copy(
                        progress = true
                    )

                    is Msg.ModsLoadingFailed -> copy(
                        progress = false
                    )
                }
            }
        ) {}


    private sealed interface Action {
        object LoadGame : Action
        object LoadMods : Action
    }

    private sealed interface Msg {
        data class ActiveGameLoaded(val game: GameInfo) : Msg
        data class ModsLoadingCompleted(val mods: List<ModInfo>) : Msg
        object ModsLoadingStarted : Msg
        object ModsLoadingFailed : Msg
    }
}
