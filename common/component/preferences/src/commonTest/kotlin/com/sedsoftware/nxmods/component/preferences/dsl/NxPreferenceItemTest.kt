package com.sedsoftware.nxmods.component.preferences.dsl

import kotlin.test.Test
import kotlin.test.assertTrue

class NxPreferenceItemTest {

    private val testPrefs: NxPreferences = nxPrefsRoot {
        group {
            category = NxPreferenceCategory.CONTENT

            switch {
                key = NxPreferenceKeyUnique.ENABLE_NSWF
                value = false
            }

            button {
                key = NxPreferenceKeyUnique.MANAGE_GAMES
            }
        }

        group {
            category = NxPreferenceCategory.NONE

            button {
                key = NxPreferenceKeyUnique.NONE
            }

            button {
                key = NxPreferenceKeyUnique.NONE
            }

            button {
                key = NxPreferenceKeyUnique.NONE
            }
        }
    }

    @Test
    fun testDsl() {
        assertTrue { testPrefs.groups.isNotEmpty() }

        testPrefs.groups.forEach { group ->
            assertTrue { group.items.isNotEmpty() }
        }
    }

    @Test
    fun updateValueTest() {
        val targetKey = NxPreferenceKeyUnique.ENABLE_NSWF
        val newPrefs = testPrefs.updateBoolean(key = targetKey, value = true)

        newPrefs.groups.first().items.forEach { item ->
            if (item.key == targetKey) {
                assertTrue { item is NxPreferenceSwitch }
                assertTrue { (item as? NxPreferenceSwitch)?.value == true }
            }
        }
    }
}
