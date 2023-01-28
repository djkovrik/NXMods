package com.sedsoftware.nxmods.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.subject.publish.PublishSubject
import com.sedsoftware.nxmods.component.auth.NxModsAuth
import com.sedsoftware.nxmods.component.auth.integration.NxAuthComponent
import com.sedsoftware.nxmods.component.gameselector.NxModsGameSelector
import com.sedsoftware.nxmods.component.gameselector.integration.NxGameSelectorComponent
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.component.modlist.integration.NxModsListComponent
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.domain.type.ModListType
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.NxModsRoot.Child
import com.sedsoftware.nxmods.utils.Consumer

class NxModsRootComponent internal constructor(
    componentContext: ComponentContext,
    private val nxModsAuth: (ComponentContext, Consumer<NxModsAuth.Output>) -> NxModsAuth,
    private val nxModsGameSelector: (ComponentContext, Consumer<NxModsGameSelector.Output>) -> NxModsGameSelector,
    private val nxModsList: (ComponentContext, Consumer<NxModsList.Output>) -> NxModsList,
) : NxModsRoot, ComponentContext by componentContext {

    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
        nxModsApi: NxModsApi,
        nxModsDatabase: NxModsDatabase,
        nxModsSettings: NxModsSettings
    ) : this(
        componentContext = componentContext,
        nxModsAuth = { childContext, output ->
            NxAuthComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                api = nxModsApi,
                settings = nxModsSettings,
                output = output
            )
        },
        nxModsGameSelector = { childContext, output ->
            NxGameSelectorComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                api = nxModsApi,
                db = nxModsDatabase,
                settings = nxModsSettings,
                output = output
            )
        },
        nxModsList = { childContext, output ->
            NxModsListComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                api = nxModsApi,
                settings = nxModsSettings,
                listType = ModListType.LATEST_ADDED,
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
            is Configuration.GameSelector -> Child.GameSelector(nxModsGameSelector(componentContext, Consumer(::onGameSelectorOutput)))
            is Configuration.ModsList -> Child.ModsList(nxModsList(componentContext, Consumer(::onModsListOutput)))
        }

    private fun onAuthOutput(output: NxModsAuth.Output): Unit =
        when (output) {
            is NxModsAuth.Output.NavigateToGameSelectionScreen -> navigation.replaceCurrent(Configuration.GameSelector)
            is NxModsAuth.Output.NavigateToHomeScreen -> navigation.replaceCurrent(Configuration.ModsList)
            is NxModsAuth.Output.ErrorCaught -> errorHandler.consume(output.throwable, messages)
        }

    private fun onGameSelectorOutput(output: NxModsGameSelector.Output): Unit =
        when (output) {
            is NxModsGameSelector.Output.NavigateToHomeScreen -> navigation.replaceCurrent(Configuration.ModsList)
            is NxModsGameSelector.Output.ErrorCaught -> errorHandler.consume(output.throwable, messages)
        }

    private fun onModsListOutput(output: NxModsList.Output): Unit =
        when (output) {
            is NxModsList.Output.OpenModInfo -> Unit
            is NxModsList.Output.ErrorCaught -> errorHandler.consume(output.throwable, messages)
        }

    private sealed class Configuration : Parcelable {
        @Parcelize
        object Auth : Configuration()

        @Parcelize
        object GameSelector : Configuration()

        @Parcelize
        object ModsList : Configuration()
    }
}
