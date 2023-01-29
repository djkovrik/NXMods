package com.sedsoftware.nxmods.component.home.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.labels
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.home.NxHome
import com.sedsoftware.nxmods.component.home.NxHome.Child
import com.sedsoftware.nxmods.component.home.NxHome.Model
import com.sedsoftware.nxmods.component.home.NxHome.Output
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherDb
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherManager
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherSettings
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Label
import com.sedsoftware.nxmods.component.home.store.HomeScreenStoreProvider
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.utils.asValue

class NxHomeComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val db: NxModsDatabase,
    private val settings: NxModsSettings,
    private val output: Consumer<Output>
) : NxHome, ComponentContext by componentContext {

    private val store: HomeScreenStore =
        instanceKeeper.getStore {
            HomeScreenStoreProvider(
                storeFactory = storeFactory,
                manager = NxModsGameSwitcherManager(
                    db = object : NxModsGameSwitcherDb {
                        override fun observeGamesList(): Observable<List<GameInfo>> =
                            db.observeGamesList()
                    },
                    settings = object : NxModsGameSwitcherSettings {
                        override var selectedGameName: String =
                            settings.currentGameName

                        override var selectedGameDomain: String =
                            settings.currentGameDomain
                    }
                )
            ).create()
        }

    init {
        val disposable: Disposable = store.labels
            .subscribeOn(mainScheduler)
            .subscribe { label ->
                when (label) {
                    is Label.ErrorCaught -> output(Output.ErrorCaught(label.throwable))
                }
            }

        lifecycle.doOnDestroy {
            disposable.dispose()
        }
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override val childStack: Value<ChildStack<*, Child>>
        get() = TODO("Not yet implemented")

    override fun onLatestAddedTabClicked() {
        TODO("Not yet implemented")
    }

    override fun onLatestUpdatedTabClicked() {
        TODO("Not yet implemented")
    }

    override fun onTrendingTabClicked() {
        TODO("Not yet implemented")
    }
}
