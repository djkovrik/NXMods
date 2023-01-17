package com.sedsoftware.nxmods.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.subject.publish.PublishSubject
import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.integration.NxModsAuthComponent
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.NxModsRoot.Child
import com.sedsoftware.nxmods.utils.Consumer

class NxModsRootComponent internal constructor(
    componentContext: ComponentContext,
    private val nxModsAuth: (ComponentContext, Consumer<NxModsAuth.Output>) -> NxModsAuth
) : NxModsRoot, ComponentContext by componentContext {

    @Suppress("UnusedPrivateMember")
    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        nxModsApi: NxModsApi,
        nxModsDatabase: NxModsDatabase,
        nxModsSettings: NxModsSettings
    ) : this(
        componentContext = componentContext,
        nxModsAuth = { childContext, output ->
            NxModsAuthComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                api = nxModsApi,
                settings = nxModsSettings,
                output = output
            )
        }
    )

    private val errorHandler: NxModsErrorHandler = NxModsErrorHandler()
    private val navigation: StackNavigation<Configuration> = StackNavigation()

    private val stack: Value<ChildStack<Configuration, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Configuration.Auth,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val childStack: Value<ChildStack<*, Child>> = stack

    override val messages: PublishSubject<String> = PublishSubject()

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Child =
        when (configuration) {
            is Configuration.Auth -> Child.Auth(nxModsAuth(componentContext, Consumer(::onAuthOutput)))
        }

    private fun onAuthOutput(output: NxModsAuth.Output): Unit =
        when (output) {
            is NxModsAuth.Output.NavigateToGameSelectionScreen -> Unit
            is NxModsAuth.Output.NavigateToHomeScreen -> Unit
            is NxModsAuth.Output.ErrorCaught -> errorHandler.consume(output.throwable, messages)
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Auth : Configuration()
    }
}
