package com.sedsoftware.nxmods.component.auth.infrastructure

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
import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.NxModsAuth.Model
import com.sedsoftware.nxmods.component.auth.NxModsAuth.Output
import com.sedsoftware.nxmods.component.auth.integration.stateToModel
import com.sedsoftware.nxmods.component.auth.store.AuthStore
import com.sedsoftware.nxmods.component.auth.store.AuthStore.Label
import com.sedsoftware.nxmods.component.auth.store.AuthStoreProvider
import com.sedsoftware.nxmods.component.utils.asValue
import com.sedsoftware.nxmods.domain.ApiKeyManager

class AuthComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val apiKeyManager: ApiKeyManager,
    private val output: Consumer<Output>
) : NxModsAuth, ComponentContext by componentContext {

    private val store: AuthStore =
        instanceKeeper.getStore {
            AuthStoreProvider(
                storeFactory = storeFactory,
                manager = apiKeyManager
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
