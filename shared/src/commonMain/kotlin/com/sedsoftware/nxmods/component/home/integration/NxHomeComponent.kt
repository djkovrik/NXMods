package com.sedsoftware.nxmods.component.home.integration

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.items
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.reaktive.labels
import com.arkivanov.mvikotlin.extensions.reaktive.states
import com.badoo.reaktive.base.Consumer
import com.badoo.reaktive.base.invoke
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.sedsoftware.nxmods.component.home.NxModsHome
import com.sedsoftware.nxmods.component.home.NxModsHome.Child
import com.sedsoftware.nxmods.component.home.NxModsHome.Model
import com.sedsoftware.nxmods.component.home.NxModsHome.Output
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherDb
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherManager
import com.sedsoftware.nxmods.component.home.domain.NxModsGameSwitcherSettings
import com.sedsoftware.nxmods.component.home.model.NavDrawerGame
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore
import com.sedsoftware.nxmods.component.home.store.HomeScreenStore.Label
import com.sedsoftware.nxmods.component.home.store.HomeScreenStoreProvider
import com.sedsoftware.nxmods.component.modlist.NxModsList
import com.sedsoftware.nxmods.component.modlist.integration.NxModsListComponent
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.domain.type.ModListType
import com.sedsoftware.nxmods.utils.Consumer
import com.sedsoftware.nxmods.utils.asValue

class NxHomeComponent(
    private val componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val api: NxModsApi,
    private val db: NxModsDatabase,
    private val settings: NxModsSettings,
    private val output: Consumer<Output>
) : NxModsHome, ComponentContext by componentContext {

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
                        override var selectedGameName: String
                            get() = settings.currentGameName
                            set(value) {
                                settings.currentGameName = value
                            }
                        override var selectedGameDomain: String
                            get() = settings.currentGameDomain
                            set(value) {
                                settings.currentGameDomain = value
                            }
                        override val userName: String
                            get() = settings.name
                        override val userAvatar: String
                            get() = settings.avatar
                        override val isPremium: Boolean
                            get() = settings.isPremium
                        override val isSupporter: Boolean
                            get() = settings.isSupporter
                    }
                )
            ).create()
        }

    private val backCallback = BackCallback {
        store.accept(HomeScreenStore.Intent.ShowNavDrawer(visible = false))
    }


    init {
        val labelsDisposable: Disposable = store.labels
            .subscribeOn(mainScheduler)
            .subscribe { label ->
                when (label) {
                    is Label.ErrorCaught -> output(Output.ErrorCaught(label.throwable))
                    is Label.GameSwitched -> refreshContent()
                }
            }

        val storeDisposable: Disposable = store.states
            .subscribeOn(mainScheduler)
            .subscribe { state ->
                backCallback.isEnabled = state.navDrawerVisible
            }

        lifecycle.doOnDestroy {
            labelsDisposable.dispose()
            storeDisposable.dispose()
        }

        backHandler.register(backCallback)
    }

    override val models: Value<Model> = store.asValue().map(stateToModel)


    private val nxModsListLatestAdded: (ComponentContext, Consumer<NxModsList.Output>) -> NxModsList = { childContext, childOutput ->
        NxModsListComponent(
            componentContext = childContext,
            storeFactory = storeFactory,
            api = api,
            db = db,
            settings = settings,
            listType = ModListType.LATEST_ADDED,
            output = childOutput
        )
    }

    private val nxModsListLatestUpdated: (ComponentContext, Consumer<NxModsList.Output>) -> NxModsList = { childContext, childOutput ->
        NxModsListComponent(
            componentContext = childContext,
            storeFactory = storeFactory,
            api = api,
            db = db,
            settings = settings,
            listType = ModListType.LATEST_UPDATED,
            output = childOutput
        )
    }

    private val nxModsListLatestTrending: (ComponentContext, Consumer<NxModsList.Output>) -> NxModsList = { childContext, childOutput ->
        NxModsListComponent(
            componentContext = childContext,
            storeFactory = storeFactory,
            api = api,
            db = db,
            settings = settings,
            listType = ModListType.TRENDING,
            output = childOutput
        )
    }

    private val navigation: StackNavigation<Config> = StackNavigation()

    private val stack: Value<ChildStack<Config, Child>> =
        childStack(
            source = navigation,
            initialStack = { listOf(Config.LatestAdded) },
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun createChild(configuration: Config, componentContext: ComponentContext): Child =
        when (configuration) {
            is Config.LatestAdded -> Child.LatestAdded(nxModsListLatestAdded(componentContext, Consumer(::onModsListOutput)))
            is Config.LatestUpdated -> Child.LatestUpdated(nxModsListLatestUpdated(componentContext, Consumer(::onModsListOutput)))
            is Config.Trending -> Child.Trending(nxModsListLatestTrending(componentContext, Consumer(::onModsListOutput)))
        }

    override fun onLatestAddedTabClicked() {
        navigation.bringToFront(Config.LatestAdded)
    }

    override fun onLatestUpdatedTabClicked() {
        navigation.bringToFront(Config.LatestUpdated)
    }

    override fun onTrendingTabClicked() {
        navigation.bringToFront(Config.Trending)
    }

    override fun onDrawerGameClicked(game: NavDrawerGame) {
        store.accept(HomeScreenStore.Intent.ShowNavDrawer(visible = false))
        store.accept(HomeScreenStore.Intent.SelectGame(game.name, game.domain))
    }

    override fun onPreferenceIconClicked() {
        output(Output.PreferenceScreenRequested)
    }

    override fun onPreferencesChanged() {
        refreshContent()
    }

    override fun onNavDrawerRequested(show: Boolean) {
        store.accept(HomeScreenStore.Intent.ShowNavDrawer(visible = show))
    }

    private fun onModsListOutput(childOutput: NxModsList.Output): Unit =
        when (childOutput) {
            is NxModsList.Output.OpenModInfo -> output(Output.ModInfoRequested(childOutput.id, childOutput.domain, childOutput.categoryId))
            is NxModsList.Output.ErrorCaught -> output(Output.ErrorCaught(childOutput.throwable))
        }

    private fun refreshContent() {
        childStack.items.forEach {
            when (val child = it.instance) {
                is Child.LatestAdded -> child.component.onRefreshRequest()
                is Child.LatestUpdated -> child.component.onRefreshRequest()
                is Child.Trending -> child.component.onRefreshRequest()
                else -> Unit
            }
        }
    }

    private sealed interface Config : Parcelable {
        @Parcelize
        object LatestAdded : Config

        @Parcelize
        object LatestUpdated : Config

        @Parcelize
        object Trending : Config
    }
}
