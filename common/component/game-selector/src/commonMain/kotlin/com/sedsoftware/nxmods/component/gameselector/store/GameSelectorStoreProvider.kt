@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.gameselector.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.gameselector.domain.NxModsGamesManager
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.Intent
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.Label
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.State
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.exception.BookmarkGameException
import com.sedsoftware.nxmods.domain.exception.FetchLocalGameListException
import com.sedsoftware.nxmods.domain.exception.FetchRemoteGameListException

internal class GameSelectorStoreProvider(
    private val storeFactory: StoreFactory,
    private val manager: NxModsGamesManager,
    private val observeScheduler: Scheduler = mainScheduler
) {

    fun create(autoInit: Boolean = true): GameSelectorStore =
        object : GameSelectorStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "GameSelectorStore",
            initialState = State(),
            autoInit = autoInit,
            bootstrapper = reaktiveBootstrapper {
                dispatch(Action.ObserveLocalGamesList)
                dispatch(Action.FetchRemoteGamesList)
            },
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.ObserveLocalGamesList> {
                    manager.observeGamesList()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onNext = { games ->
                                dispatch(Msg.GamesLoadingCompleted(games))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(FetchLocalGameListException(throwable)))
                            }
                        )
                }

                onAction<Action.FetchRemoteGamesList> {
                    manager.fetchGamesList()
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onError = { throwable ->
                                publish(Label.ErrorCaught(FetchRemoteGameListException(throwable)))
                            }
                        )
                }

                onIntent<Intent.BookmarkGame> {
                    manager.toggleBookmark(it.domain)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onError = { throwable ->
                                publish(Label.ErrorCaught(BookmarkGameException(throwable)))
                            }
                        )
                }

                onIntent<Intent.ClickNextButton> {
                    publish(Label.NextScreenRequested)
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.GamesLoadingStarted -> copy(
                        progressVisible = true
                    )

                    is Msg.GamesLoadingCompleted -> copy(
                        games = msg.games,
                        progressVisible = false,
                        bookmarkedGamesCounter = games.count { it.isBookmarked }
                    )

                    is Msg.GamesLoadingFailed -> copy(
                        progressVisible = false
                    )

                    is Msg.BookmarkRequestCompleted -> copy(
                        bookmarkedGamesCounter = games.count { it.isBookmarked }
                    )
                }
            }
        ) {}

    private sealed interface Action {
        object ObserveLocalGamesList : Action
        object FetchRemoteGamesList : Action
    }

    private sealed interface Msg {
        object GamesLoadingStarted : Msg
        data class GamesLoadingCompleted(val games: List<GameInfo>) : Msg
        object GamesLoadingFailed : Msg
        object BookmarkRequestCompleted : Msg
    }
}
