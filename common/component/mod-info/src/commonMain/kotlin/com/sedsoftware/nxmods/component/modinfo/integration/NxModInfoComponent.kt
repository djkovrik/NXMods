package com.sedsoftware.nxmods.component.modinfo.integration

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
import com.sedsoftware.nxmods.component.modinfo.NxModsInfo
import com.sedsoftware.nxmods.component.modinfo.NxModsInfo.Model
import com.sedsoftware.nxmods.component.modinfo.NxModsInfo.Output
import com.sedsoftware.nxmods.component.modinfo.domain.NxModsInfoApi
import com.sedsoftware.nxmods.component.modinfo.domain.NxModsInfoDb
import com.sedsoftware.nxmods.component.modinfo.domain.NxModsInfoManager
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStore.Label
import com.sedsoftware.nxmods.component.modinfo.store.ModInfoStoreProvider
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.utils.asValue

class NxModInfoComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val modId: Long,
    private val gameDomain: String,
    private val categoryId: Long,
    private val output: Consumer<Output>
) : NxModsInfo, ComponentContext by componentContext {

    private val store: ModInfoStore =
        instanceKeeper.getStore {
            ModInfoStoreProvider(
                storeFactory = storeFactory,
                manager = NxModsInfoManager(
                    api = object : NxModsInfoApi {
                        override fun getMod(domain: String, id: Long): Observable<ModInfo> =
                            api.getMod(domain, id)

                        override fun track(domain: String, id: Long): Completable =
                            api.track(domain, id)

                        override fun untrack(domain: String, id: Long): Completable =
                            api.untrack(domain, id)

                        override fun endorse(domain: String, id: Long, version: String): Completable =
                            api.endorse(domain, id, version)

                        override fun unendorse(domain: String, id: Long, version: String): Completable =
                            api.unendorse(domain, id, version)

                        override fun getChangelog(domain: String, id: Long): Observable<List<ChangelogItem>> =
                            api.getChangelog(domain, id)
                    },
                    db = object : NxModsInfoDb {
                        override fun track(domain: String, modId: Long, track: Boolean): Completable =
                            db.track(domain, modId, track)

                        override fun endorse(domain: String, modId: Long, endorse: Boolean): Completable =
                            db.endorse(domain, modId, endorse)

                        override fun getCachedModData(domain: String, modId: Long, categoryId: Long): Observable<CachedModData> =
                            db.getCachedModData(domain, modId, categoryId)
                    },
                ),
                modId = modId,
                gameDomain = gameDomain,
                categoryId = categoryId
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

    override fun onEndorseClicked() {
        store.accept(ModInfoStore.Intent.Endorse)
    }

    override fun onUnendorseClicked() {
        store.accept(ModInfoStore.Intent.Unendorse)
    }

    override fun onTrackClicked() {
        store.accept(ModInfoStore.Intent.Track)
    }

    override fun onUntrackClicked() {
        store.accept(ModInfoStore.Intent.Untrack)
    }
}
