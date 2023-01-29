package com.sedsoftware.nxmods.settings

import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import kotlin.test.Test
import kotlin.test.assertTrue

class NxModsSettingsTest {

    private val component: SettingsFeatureComponent = SettingsFeatureComponentMock()
    private val settings: NxModsSettings = component.settings

    @Test
    fun settingsReadWrite_test() {
        with(settings) {
            name = "User name"
            avatar = "http://some.link"
            isPremium = true
            isSupporter = true
            apiKey = "api-key"
            isProfileValidated = true
            currentGameDomain = "domain"
        }

        assertTrue(settings.name == "User name", "Settings should have name")
        assertTrue(settings.avatar == "http://some.link", "Settings should have avatar")
        assertTrue(settings.apiKey == "api-key", "Settings should have api key")
        assertTrue(settings.isPremium, "Settings should have premium == true")
        assertTrue(settings.isSupporter, "Settings should have supporter == true")
        assertTrue(settings.isProfileValidated, "Settings should have valid flag == true")
    }
}
