package com.sedsoftware.nxmods.root.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.items
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
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
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.component.home.integration.NxHomeComponent
import com.sedsoftware.nxmods.component.preferences.NxModsPreferences
import com.sedsoftware.nxmods.component.preferences.integration.NxModsPreferencesComponent
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.root.NxModsRoot
import com.sedsoftware.nxmods.root.NxModsRoot.Child
import com.sedsoftware.nxmods.utils.Consumer

class NxModsRootComponent internal constructor(
    componentContext: ComponentContext,
    private val nxModsAuth: (ComponentContext, Consumer<NxModsAuth.Output>) -> NxModsAuth,
    private val nxModsGameSelector: (ComponentContext, fromPreferences: Boolean, Consumer<NxModsGameSelector.Output>) -> NxModsGameSelector,
    private val nxHome: (ComponentContext, Consumer<NxModsHome.Output>) -> NxModsHome,
    private val nxPreferences: (ComponentContext, Consumer<NxModsPreferences.Output>) -> NxModsPreferences,
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
        nxModsGameSelector = { childContext, fromPreferences, output ->
            NxGameSelectorComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                api = nxModsApi,
                db = nxModsDatabase,
                settings = nxModsSettings,
                fromPreferences = fromPreferences,
                output = output
            )
        },
        nxHome = { childContext, output ->
            NxHomeComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
                api = nxModsApi,
                db = nxModsDatabase,
                settings = nxModsSettings,
                output = output
            )
        },
        nxPreferences = { childContext, output ->
            NxModsPreferencesComponent(
                componentContext = childContext,
                storeFactory = storeFactory,
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
            is Configuration.Auth ->
                Child.Auth(nxModsAuth(componentContext, Consumer(::onAuthOutput)))

            is Configuration.GameSelector ->
                Child.GameSelector(nxModsGameSelector(componentContext, configuration.fromPreferences, Consumer(::onGameSelectorOutput)))

            is Configuration.Home ->
                Child.Home(nxHome(componentContext, Consumer(::onHomeScreenOutput)))

            is Configuration.Preferences ->
                Child.Preferences(nxPreferences(componentContext, Consumer(::onPreferenceScreenOutput)))
        }

    private fun onAuthOutput(output: NxModsAuth.Output): Unit =
        when (output) {
            is NxModsAuth.Output.NavigateToGameSelectionScreen ->
                navigation.replaceCurrent(Configuration.GameSelector(fromPreferences = false))

            is NxModsAuth.Output.NavigateToHomeScreen ->
                navigation.replaceCurrent(Configuration.Home)

            is NxModsAuth.Output.ErrorCaught ->
                errorHandler.consume(output.throwable, messages)
        }

    private fun onGameSelectorOutput(output: NxModsGameSelector.Output): Unit =
        when (output) {
            is NxModsGameSelector.Output.NavigateToHomeScreen ->
                navigation.replaceCurrent(Configuration.Home)

            is NxModsGameSelector.Output.CloseGameSelector ->
                navigation.pop()

            is NxModsGameSelector.Output.ErrorCaught ->
                errorHandler.consume(output.throwable, messages)
        }

    private fun onHomeScreenOutput(output: NxModsHome.Output): Unit =
        when (output) {
            is NxModsHome.Output.ErrorCaught ->
                errorHandler.consume(output.throwable, messages)

            is NxModsHome.Output.PreferenceScreenRequested ->
                navigation.push(Configuration.Preferences)
        }

    private fun onPreferenceScreenOutput(output: NxModsPreferences.Output): Unit =
        when (output) {
            is NxModsPreferences.Output.ErrorCaught ->
                errorHandler.consume(output.throwable, messages)

            is NxModsPreferences.Output.GamesSelectorRequested ->
                navigation.push(Configuration.GameSelector(fromPreferences = true))

            is NxModsPreferences.Output.ScreenClosed ->
                navigation.pop()

            is NxModsPreferences.Output.PreferencesChanged ->
                onPreferenceChangedEvent()
        }

    private fun onPreferenceChangedEvent() {
        childStack.items.forEach { child ->
            (child.instance as? Child.Home)?.component?.onPreferencesChanged()
        }
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        object Auth : Configuration

        @Parcelize
        data class GameSelector(val fromPreferences: Boolean) : Configuration

        @Parcelize
        object Home : Configuration

        @Parcelize
        object Preferences : Configuration
    }
}
