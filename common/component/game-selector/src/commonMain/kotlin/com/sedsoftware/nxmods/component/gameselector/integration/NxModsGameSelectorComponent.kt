package com.sedsoftware.nxmods.component.gameselector.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.labels
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector.Model
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector.Output
import com.sedsoftware.nxmods.component.gameselector.domain.NxModsGamesApi
import com.sedsoftware.nxmods.component.gameselector.domain.NxModsGamesDb
import com.sedsoftware.nxmods.component.gameselector.domain.NxModsGamesManager
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStore.Label
import com.sedsoftware.nxmods.component.gameselector.store.GameSelectorStoreProvider
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.utils.asValue

class NxModsGameSelectorComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val settings: NxModsSettings,
    private val output: Consumer<Output>
) : NxModsGameSelector, ComponentContext by componentContext {

    private val store: GameSelectorStore =
        instanceKeeper.getStore {
            GameSelectorStoreProvider(
                storeFactory = storeFactory,
                manager = NxModsGamesManager(
                    api = object : NxModsGamesApi {
                        override fun getGames(): Observable<List<GameInfo>> =
                            api.getGames()
                    },
                    db = object : NxModsGamesDb {
                        override fun observeGamesList(): Observable<List<GameInfo>> =
                            db.observeGamesList()

                        override fun saveGames(items: List<GameInfo>): Completable =
                            db.saveGames(items)

                        override fun toggleBookmark(domain: String): Completable =
                            db.toggleBookmark(domain)
                    },
                    settings = settings
                )
            ).create()
        }

    init {
        val disposable: Disposable = store.labels
            .subscribeOn(mainScheduler)
            .subscribe { label ->
                when (label) {
                    is Label.NextScreenRequested -> output(Output.NavigateToHomeScreen)
                    is Label.ErrorCaught -> output(Output.ErrorCaught(label.throwable))
                }
            }

        lifecycle.doOnDestroy {
            disposable.dispose()
        }
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onBookmarkClicked(domain: String) {
        store.accept(GameSelectorStore.Intent.BookmarkGame(domain))
    }

    override fun onNextButtonClicked() {
        output(Output.NavigateToHomeScreen)
    }
}
