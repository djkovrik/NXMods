package com.sedsoftware.nxmods.component.preferences.integration

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
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.preferences.NxModsPreferences
import com.sedsoftware.nxmods.component.preferences.NxModsPreferences.Model
import com.sedsoftware.nxmods.component.preferences.NxModsPreferences.Output
import com.sedsoftware.nxmods.component.preferences.domain.NxModsPreferenceManager
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStore.Label
import com.sedsoftware.nxmods.component.preferences.store.PreferencesStoreProvider
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.utils.asValue

class NxModsPreferencesComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val settings: NxModsSettings,
    private val output: Consumer<Output>
) : NxModsPreferences, ComponentContext by componentContext {

    private val store: PreferencesStore =
        instanceKeeper.getStore {
            PreferencesStoreProvider(
                storeFactory = storeFactory,
                manager = NxModsPreferenceManager(settings = settings)
            ).create()
        }

    init {
        val disposable: Disposable = store.labels
            .subscribeOn(mainScheduler)
            .subscribe { label ->
                when (label) {
                    is Label.ErrorCaught -> output(Output.ErrorCaught(label.throwable))
                    is Label.GameSelectorRequested -> output(Output.GamesSelectorRequested)
                    is Label.PreferencesChanged -> output(Output.PreferencesChanged)
                    is Label.ScreenClosed -> output(Output.ScreenClosed)
                }
            }

        lifecycle.doOnDestroy {
            disposable.dispose()
        }
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onButtonClicked(key: NxPreferenceKeyUnique) {
        store.accept(PreferencesStore.Intent.ClickButton(key))
    }

    override fun onSwitchChanged(key: NxPreferenceKeyUnique, value: Boolean) {
        store.accept(PreferencesStore.Intent.ChangeSwitch(key, value))
    }
}
