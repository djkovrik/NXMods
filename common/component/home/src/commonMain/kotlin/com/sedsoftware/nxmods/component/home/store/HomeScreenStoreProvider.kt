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
import com.sedsoftware.nxmods.component.home.model.HomeScreenData
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
        Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "HomeScreenStore",
            initialState = State(),
            autoInit = autoInit,
            bootstrapper = reaktiveBootstrapper {
                dispatch(Action.FetchBasicInfo)
                dispatch(Action.ObserveLocalGamesList)
            },
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.FetchBasicInfo> {
                    manager.getBaseInfo()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onSuccess = { data ->
                                dispatch(Msg.UserDataLoaded(data))
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
                                dispatch(Msg.GameSelected(intent.name, intent.domain))
                                publish(Label.GameSwitched)
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(SwitchSelectedGameException(throwable)))
                            })
                }

                onIntent<Intent.ShowNavDrawer> { dispatch(Msg.NavDrawerVisibilityChanged(it.visible)) }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.UserDataLoaded -> copy(
                        user = msg.info.user,
                        currentGame = msg.info.currentGame,
                        currentDomain = msg.info.currentDomain
                    )

                    is Msg.GameSelected -> copy(
                        currentGame = msg.name,
                        currentDomain = msg.domain
                    )

                    is Msg.GamesFetched -> copy(
                        availableGames = msg.games
                            .sortedBy { it.name }
                            .sortedBy { it.domain == currentDomain }
                    )

                    is Msg.NavDrawerVisibilityChanged -> copy(
                        navDrawerVisible = msg.visible
                    )
                }
            }) {}

    private sealed interface Action {
        object FetchBasicInfo : Action
        object ObserveLocalGamesList : Action
    }

    private sealed interface Msg {
        data class UserDataLoaded(val info: HomeScreenData) : Msg
        data class GameSelected(val name: String, val domain: String) : Msg
        data class GamesFetched(val games: List<GameInfo>) : Msg
        data class NavDrawerVisibilityChanged(val visible: Boolean) : Msg
    }
}
