@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.home.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.observeOn
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherManager
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Intent
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Label
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.exception.FetchLocalGameListException
import com.sedsoftware.nxmods.domain.exception.SwitchSelectedGameException

internal class HomeScreenStoreProvider(
    private val storeFactory: StoreFactory,
    private val manager: NxModsGameSwitcherManager,
    private val observeScheduler: Scheduler = mainScheduler
) {

    fun create(autoInit: Boolean = true): HomeScreenStore = object : HomeScreenStore,
        Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(name = "HomeScreenStore",
            initialState = State(),
            autoInit = autoInit,
            bootstrapper = reaktiveBootstrapper {
                dispatch(Action.FetchCurrentGameName)
                dispatch(Action.FetchCurrentGameDomain)
                dispatch(Action.ObserveLocalGamesList)
            },
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.FetchCurrentGameName> {
                    manager.getActiveName()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onSuccess = { game ->
                                dispatch(Msg.GameSelected(game))
                            }, onError = { throwable ->
                                publish(Label.ErrorCaught(SwitchSelectedGameException(throwable)))
                            })
                }

                onAction<Action.FetchCurrentGameDomain> {
                    manager.getActiveDomain()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onSuccess = { game ->
                                dispatch(Msg.DomainSelected(game))
                            }, onError = { throwable ->
                                publish(Label.ErrorCaught(SwitchSelectedGameException(throwable)))
                            })
                }

                onAction<Action.ObserveLocalGamesList> {
                    manager.watchForBookmarkedGames()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onNext = { games ->
                                dispatch(Msg.GamesFetched(games))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(FetchLocalGameListException(throwable)))
                            })
                }

                onIntent<Intent.SelectGame> { intent ->
                    manager.switchTo(intent.name, intent.domain)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onComplete = {
                                dispatch(Msg.GameSelected(intent.name))
                                dispatch(Msg.DomainSelected(intent.domain))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(SwitchSelectedGameException(throwable)))
                            })
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.GameSelected -> copy(
                        currentGame = msg.name
                    )

                    is Msg.DomainSelected -> copy(
                        currentDomain = msg.domain
                    )

                    is Msg.GamesFetched -> copy(
                        availableGames = msg.games
                    )

                }
            }) {}

    private sealed interface Action {
        object FetchCurrentGameName : Action
        object FetchCurrentGameDomain : Action
        object ObserveLocalGamesList : Action
    }

    private sealed interface Msg {
        data class GameSelected(val name: String) : Msg
        data class DomainSelected(val domain: String) : Msg
        data class GamesFetched(val games: List<GameInfo>) : Msg
    }
}
