@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.gameselector.store

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveBootstrapper
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.completable.doOnBeforeSubscribe
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
                                dispatch(Msg.GamesFetched(games))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(FetchLocalGameListException(throwable)))
                            }
                        )
                }

                onAction<Action.FetchRemoteGamesList> {
                    manager.fetchGamesList()
                        .observeOn(observeScheduler)
                        .doOnBeforeSubscribe { dispatch(Msg.GamesLoadingStarted) }
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

                onIntent<Intent.SearchQueryInput> { dispatch(Msg.TextEntered(it.query)) }
                onIntent<Intent.OpenSearchClick> { dispatch(Msg.SearchOpened) }
                onIntent<Intent.CloseSearchClick> { dispatch(Msg.SearchClosed) }

                onIntent<Intent.NextButtonClick> {
                    publish(Label.NextScreenRequested)
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.GamesLoadingStarted -> copy(
                        progressVisible = true
                    )

                    is Msg.GamesFetched -> copy(
                        loadedGames = msg.games,
                        displayedGames = msg.games,
                        progressVisible = msg.games.isEmpty(),
                        bookmarkedGamesCounter = msg.games.count { it.isBookmarked }
                    )

                    is Msg.GamesLoadingFailed -> copy(
                        progressVisible = false
                    )

                    is Msg.TextEntered -> copy(
                        displayedGames = if (msg.query.isNotEmpty()) {
                            loadedGames.filter { it.name.lowercase().contains(msg.query.lowercase()) || it.isBookmarked }
                        } else {
                            loadedGames
                        },
                        searchQueryInput = msg.query
                    )

                    is Msg.SearchOpened -> copy(
                        searchFieldVisible = true
                    )

                    is Msg.SearchClosed -> copy(
                        searchFieldVisible = false,
                        displayedGames = loadedGames,
                        searchQueryInput = ""
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
        data class GamesFetched(val games: List<GameInfo>) : Msg
        object GamesLoadingFailed : Msg
        data class TextEntered(val query: String) : Msg
        object SearchOpened : Msg
        object SearchClosed : Msg
    }
}
