package com.sedsoftware.nxmods.settings.internal

import com.russhwolf.settings.Settings
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

internal class NxSharedSettings(
    private val settings: Settings
) : NxModsSettings {

    override var name: String
        get() = settings.getValue(PREF_KEY_NAME, "")
        set(value) {
            settings.setValue(PREF_KEY_NAME, value)
        }

    override var avatar: String
        get() = settings.getValue(PREF_KEY_AVATAR, "")
        set(value) {
            settings.setValue(PREF_KEY_AVATAR, value)
        }

    override var isPremium: Boolean
        get() = settings.getValue(PREF_KEY_PREMIUM, false)
        set(value) {
            settings.setValue(PREF_KEY_PREMIUM, value)
        }

    override var isSupporter: Boolean
        get() = settings.getValue(PREF_KEY_SUPPORTER, false)
        set(value) {
            settings.setValue(PREF_KEY_SUPPORTER, value)
        }

    override var apiKey: String
        get() = settings.getValue(PREF_KEY_API, "")
        set(value) {
            settings.setValue(PREF_KEY_API, value)
        }

    override var isProfileValid: Boolean
        get() = settings.getValue(PREF_KEY_VALID, false)
        set(value) {
            settings.setValue(PREF_KEY_VALID, value)
        }

    override var currentDomain: String
        get() = settings.getValue(PREF_KEY_DOMAIN, "")
        set(value) {
            settings.setValue(PREF_KEY_DOMAIN, value)
        }

    private fun Settings.setValue(key: String, value: Any) {
        when (value) {
            is String -> this.putString(key, value)
            is Int -> this.putInt(key, value)
            is Boolean -> this.putBoolean(key, value)
            is Float -> this.putFloat(key, value)
            is Long -> this.putLong(key, value)
            else -> throw UnsupportedOperationException("Not implemented yet")
        }
    }

    private inline fun <reified T : Any> Settings.getValue(key: String, defaultValue: T? = null): T =
        when (T::class) {
            String::class -> getString(key, defaultValue as? String ?: "") as T
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
            Long::class -> getLong(key, defaultValue as? Long ?: -1L) as T
            else -> throw UnsupportedOperationException("Not implemented yet")
        }

    private companion object {
        const val PREF_KEY_NAME = "pref_key_name"
        const val PREF_KEY_AVATAR = "pref_key_avatar"
        const val PREF_KEY_PREMIUM = "pref_key_premium"
        const val PREF_KEY_SUPPORTER = "pref_key_supporter"
        const val PREF_KEY_API = "pref_key_api"
        const val PREF_KEY_VALID = "pref_key_valid"
        const val PREF_KEY_DOMAIN = "pref_key_domain"
    }
}
