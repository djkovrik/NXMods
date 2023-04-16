package com.sedsoftware.nxmods.component.modlist.integration

import com.arkivanov.decompose.ComponentContext
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
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.component.modlist.NxModsList.Model
import com.sedsoftware.nxmods.component.modlist.NxModsList.Output
import com.sedsoftware.nxmods.component.modlist.domain.NxModsListDb
import com.sedsoftware.nxmods.component.modlist.domain.NxModsListsApi
import com.sedsoftware.nxmods.component.modlist.domain.NxModsListsManager
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore
import com.sedsoftware.nxmods.component.modlist.store.ModsListStore.Label
import com.sedsoftware.nxmods.component.modlist.store.ModsListStoreProvider
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.domain.type.ModListType
import com.sedsoftware.nxmods.utils.asValue

class NxModsListComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val settings: NxModsSettings,
    private val listType: ModListType,
    private val output: Consumer<Output>
) : NxModsList, ComponentContext by componentContext {

    private val store: ModsListStore =
        instanceKeeper.getStore {
            ModsListStoreProvider(
                storeFactory = storeFactory,
                listType = listType,
                manager = NxModsListsManager(
                    api = object : NxModsListsApi {
                        override fun getLatestAdded(domain: String): Observable<List<ModInfo>> =
                            api.getLatestAdded(domain)

                        override fun getLatestUpdated(domain: String): Observable<List<ModInfo>> =
                            api.getLatestUpdated(domain)

                        override fun getTrending(domain: String): Observable<List<ModInfo>> =
                            api.getTrending(domain)
                    },
                    db = object : NxModsListDb {
                        override fun getActiveGameInfo(domain: String): Observable<GameInfo> =
                            db.observeGame(domain)
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
                    is Label.ErrorCaught -> output(Output.ErrorCaught(label.throwable))
                }
            }

        lifecycle.doOnDestroy {
            disposable.dispose()
        }
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onModInfoClick(domain: String, modId: Long, categoryId: Long) {
        output(Output.OpenModInfo(domain, modId, categoryId))
    }

    override fun onRefreshRequest() {
        store.accept(ModsListStore.Intent.Refresh)
    }
}
