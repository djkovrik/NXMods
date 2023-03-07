package com.sedsoftware.nxmods.component.preferences.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleFromFunction
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceCategory
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferenceKeyUnique
import com.sedsoftware.nxmods.component.preferences.dsl.NxPreferences
import com.sedsoftware.nxmods.component.preferences.dsl.button
import com.sedsoftware.nxmods.component.preferences.dsl.group
import com.sedsoftware.nxmods.component.preferences.dsl.nxPrefsRoot
import com.sedsoftware.nxmods.component.preferences.dsl.switch
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

internal class NxModsPreferenceManager(
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun buildPreferenceScreen(): Single<NxPreferences> =
        singleFromFunction {
            nxPrefsRoot {
                group {
                    category = NxPreferenceCategory.CONTENT

                    switch {
                        key = NxPreferenceKeyUnique.ENABLE_NSWF
                        value = settings.allowNsfw
                    }

                    button {
                        key = NxPreferenceKeyUnique.MANAGE_GAMES
                    }
                }
            }
        }
            .subscribeOn(scheduler)

    fun changePreference(type: NxPreferenceKeyUnique, value: Boolean): Completable =
        completableFromFunction {
            when (type) {
                NxPreferenceKeyUnique.ENABLE_NSWF -> settings.allowNsfw = value
                else -> Unit
            }
        }
            .subscribeOn(scheduler)
}
