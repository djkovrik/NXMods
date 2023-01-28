package com.sedsoftware.nxmods.component.auth.integration

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
import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.NxModsAuth.Model
import com.sedsoftware.nxmods.component.auth.NxModsAuth.Output
import com.sedsoftware.nxmods.component.auth.domain.NxModsAuthApi
import com.sedsoftware.nxmods.component.auth.domain.NxModsAuthManager
import com.sedsoftware.nxmods.component.auth.store.AuthStore
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Label
import com.sedsoftware.nxmods.component.auth.store.AuthStoreProvider
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.utils.asValue

class NxAuthComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val api: NxModsApi,
    private val settings: NxModsSettings,
    private val output: Consumer<Output>
) : NxModsAuth, ComponentContext by componentContext {

    private val store: AuthStore =
        instanceKeeper.getStore {
            AuthStoreProvider(
                storeFactory = storeFactory,
                manager = NxModsAuthManager(
                    api = object : NxModsAuthApi {
                        override fun validateApiKey(key: String): Observable<OwnProfile> =
                            api.validateApiKey(key)
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
                    is Label.ExistingUserValidationCompleted -> output(Output.NavigateToHomeScreen)
                    is Label.NewUserValidationCompleted -> output(Output.NavigateToGameSelectionScreen)
                    is Label.ErrorCaught -> output(Output.ErrorCaught(label.throwable))
                }
            }

        lifecycle.doOnDestroy {
            disposable.dispose()
        }
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)

    override fun onTextEntered(text: String) {
        store.accept(AuthStore.Intent.InputText(text))
    }

    override fun onValidateButtonClicked() {
        store.accept(AuthStore.Intent.ClickValidateButton)
    }

    override fun onNextButtonClicked() {
        store.accept(AuthStore.Intent.ClickNextButton)
    }
}
