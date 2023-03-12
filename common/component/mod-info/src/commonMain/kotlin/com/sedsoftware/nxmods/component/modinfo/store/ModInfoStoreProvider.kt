@file:OptIn(ExperimentalMviKotlinApi::class)

package com.sedsoftware.nxmods.component.modinfo.store

import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.reaktive.reaktiveExecutorFactory
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.modinfo.domain.NxModsInfoManager
import com.sedsoftware.nxmods.component.modinfo.integration.applyModInfo
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.Intent
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.Label
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.State
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.exception.ModEndorseException
import com.sedsoftware.nxmods.domain.exception.ModInfoLoadingException
import com.sedsoftware.nxmods.domain.exception.ModTrackException

internal class ModInfoStoreProvider(
    private val storeFactory: StoreFactory,
    private val manager: NxModsInfoManager,
    private val modId: Long,
    private val gameDomain: String,
    private val categoryId: Long,
    private val observeScheduler: Scheduler = mainScheduler
) {

    fun create(autoInit: Boolean = true): ModInfoStore =
        object : ModInfoStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "ModInfoStore",
            initialState = State(id = modId, domain = gameDomain),
            autoInit = autoInit,
            bootstrapper = SimpleBootstrapper<Action>(Action.LoadInfo),
            executorFactory = reaktiveExecutorFactory {
                onAction<Action.LoadInfo> {
                    manager.getModInfo(gameDomain, modId, categoryId)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onNext = { info ->
                                dispatch(Msg.ModInfoLoaded(info))
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(ModInfoLoadingException(throwable)))
                            }
                        )
                }

                onIntent<Intent.Endorse> {
                    manager.endorse(state.domain, state.id, state.version)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onComplete = {
                                dispatch(Msg.Endorsed)
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(ModEndorseException(throwable)))
                            }
                        )
                }

                onIntent<Intent.Unendorse> {
                    manager.unendorse(state.domain, state.id, state.version)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onComplete = {
                                dispatch(Msg.Unendorsed)
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(ModEndorseException(throwable)))
                            }
                        )
                }

                onIntent<Intent.Track> {
                    manager.track(state.domain, state.id)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onComplete = {
                                dispatch(Msg.Tracked)
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(ModTrackException(throwable)))
                            }
                        )
                }

                onIntent<Intent.Untrack> {
                    manager.untrack(state.domain, state.id)
                        .observeOn(observeScheduler)
                        .subscribeScoped(
                            onComplete = {
                                dispatch(Msg.Untracked)
                            },
                            onError = { throwable ->
                                publish(Label.ErrorCaught(ModTrackException(throwable)))
                            }
                        )
                }
            },
            reducer = { msg ->
                when (msg) {
                    is Msg.ModInfoLoaded -> applyModInfo(msg.info)
                    is Msg.Endorsed -> copy(isEndorsed = true)
                    is Msg.Unendorsed -> copy(isEndorsed = false)
                    is Msg.Tracked -> copy(isTracked = true)
                    is Msg.Untracked -> copy(isTracked = false)
                }
            }
        ) {}

    private sealed interface Action {
        object LoadInfo : Action
    }

    private sealed interface Msg {
        data class ModInfoLoaded(val info: ModInfo) : Msg
        object Endorsed : Msg
        object Unendorsed : Msg
        object Tracked : Msg
        object Untracked : Msg
    }
}
