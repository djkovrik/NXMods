package com.sedsoftware.nxmods.component.auth.store

import com.arkivanov.mvikotlin.extensions.reaktive.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.subscribe
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.subject.publish.PublishSubject
import com.badoo.reaktive.test.scheduler.TestScheduler
import com.sedsoftware.nxmods.component.auth.domain.NxModsAuthApi
import com.sedsoftware.nxmods.component.auth.domain.NxModsAuthManager
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AuthStoreTest {

    private val validateSubject: PublishSubject<OwnProfile> = PublishSubject()

    private val testScheduler: Scheduler = TestScheduler()

    private var nameStub: String = ""
    private var avatarStub: String = ""
    private var isPremiumStub: Boolean = false
    private var isSupporterStub: Boolean = false
    private var apiKeyStub: String = ""
    private var isProfileValidatedStub: Boolean = false
    private var currentDomainStub: String = ""
    private var currentNameStub: String = ""
    private var allowNsfwStub: Boolean = false

    private var label: AuthStore.Label? = null

    private val manager: NxModsAuthManager =
        NxModsAuthManager(
            api = object : NxModsAuthApi {
                override fun validateApiKey(key: String): Observable<OwnProfile> {
                    return validateSubject
                }
            },
            settings = object : NxModsSettings {
                override var name: String
                    get() = nameStub
                    set(value) {
                        nameStub = value
                    }
                override var avatar: String
                    get() = avatarStub
                    set(value) {
                        avatarStub = value
                    }
                override var isPremium: Boolean
                    get() = isPremiumStub
                    set(value) {
                        isPremiumStub = value
                    }
                override var isSupporter: Boolean
                    get() = isSupporterStub
                    set(value) {
                        isSupporterStub = value
                    }
                override var apiKey: String
                    get() = apiKeyStub
                    set(value) {
                        apiKeyStub = value
                    }
                override var isProfileValidated: Boolean
                    get() = isProfileValidatedStub
                    set(value) {
                        isProfileValidatedStub = value
                    }
                override var currentGameName: String
                    get() = currentNameStub
                    set(value) {
                        currentNameStub = value
                    }
                override var currentGameDomain: String
                    get() = currentDomainStub
                    set(value) {
                        currentDomainStub = value
                    }
                override var allowNsfw: Boolean
                    get() = allowNsfwStub
                    set(value) {
                        allowNsfwStub = value
                    }
            },
            scheduler = testScheduler
        )

    private lateinit var store: AuthStore
    private lateinit var disposable: Disposable

    @BeforeTest
    fun initStore() {
        store = AuthStoreProvider(
            storeFactory = DefaultStoreFactory(),
            manager = manager,
            observeScheduler = testScheduler
        ).create(autoInit = false)

        disposable = store.labels
            .subscribeOn(testScheduler)
            .subscribe { label = it }
    }

    @AfterTest
    fun disposeStore() {
        disposable.dispose()
        store.dispose()
        label = null
    }

    @Test
    fun initialState_test() {
        with(store.state) {
            assertTrue(textInput.isEmpty(), "Text input is empty by default")
            assertTrue(progressVisible, "Progress is visible by default")
            assertEquals(apiKeyStatus, ApiKeyStatus.NOT_CHECKED, "Key status is not checked by default")
        }
    }

    @Test
    fun newUserFlow_test() {
        apiKeyStub = ""

        store.init()

        with(store.state) {
            assertTrue(textInput.isEmpty(), "Text input is empty")
            assertFalse(progressVisible, "Progress is not visible")
            assertEquals(apiKeyStatus, ApiKeyStatus.NOT_FOUND, "Key status equals to not found")
        }
    }

    @Test
    fun existingUserFlow_validKey_test() {
        apiKeyStub = "some-api-key"
        currentDomainStub = "domain"

        store.init()

        // Validation started
        assertTrue(store.state.progressVisible, "Progress is visible")

        validateSubject.onNext(
            OwnProfile(
                userId = 123,
                key = "Some key",
                name = "Some name",
                isPremium = true,
                isSupporter = true,
                email = "some@email.com",
                avatar = "http://some.avatar"
            )
        )

        with(store.state) {
            assertTrue(progressVisible, "Progress is not visible")
            assertEquals(apiKeyStatus, ApiKeyStatus.VALID, "Key status equals to valid")
        }

        assertEquals(apiKeyStub, "Some key", "Key was persisted")
        assertTrue(isProfileValidatedStub, "Profile validated")

        // Navigate to next screen
        assertEquals(label, AuthStore.Label.ExistingUserValidationCompleted, " Existing user validated")
    }

    @Test
    fun existingUserFlow_invalidKey_test() {
        apiKeyStub = "some-api-key"
        currentDomainStub = "domain"

        store.init()

        // Validation started
        assertTrue(store.state.progressVisible, "Progress is visible")

        validateSubject.onError(Throwable("Some error"))

        with(store.state) {
            assertFalse(progressVisible, "Progress is not visible")
            assertEquals(apiKeyStatus, ApiKeyStatus.INVALID, "Key status equals to invalid")
        }
    }

    @Test
    fun textInput_test() {
        apiKeyStub = ""

        store.init()

        store.accept(AuthStore.Intent.InputText("test"))

        assertEquals(store.state.textInput, "test", "Text input should be correct")
    }

    @Test
    fun newUserValidation_test() {
        apiKeyStub = ""

        store.init()
        store.accept(AuthStore.Intent.InputText("some api key haere"))
        store.accept(AuthStore.Intent.ClickValidateButton)

        // Validation started
        assertEquals(store.state.apiKeyStatus, ApiKeyStatus.VALIDATION)

        // Validation response
        validateSubject.onNext(
            OwnProfile(
                userId = 123,
                key = "Some key",
                name = "Some name",
                isPremium = true,
                isSupporter = true,
                email = "some@email.com",
                avatar = "http://some.avatar"
            )
        )

        with(store.state) {
            assertFalse(progressVisible, "Progress is not visible")
            assertEquals(apiKeyStatus, ApiKeyStatus.VALID, "Key status equals to valid")
        }

        assertEquals(apiKeyStub, "Some key", "Key was persisted")
        assertTrue(isProfileValidatedStub, "Profile validated")

        store.accept(AuthStore.Intent.ClickNextButton)

        // Navigate to next screen
        assertEquals(label, AuthStore.Label.NewUserValidationCompleted, " New user validated")
    }
}
