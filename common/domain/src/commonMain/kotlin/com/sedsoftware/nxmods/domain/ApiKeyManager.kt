package com.sedsoftware.nxmods.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.doOnAfterError
import com.badoo.reaktive.single.flatMapCompletable
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

class ApiKeyManager(
    private val api: NxModsApi,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getCurrentApiKey(): Single<String> =
        singleOf(settings.apiKey)
            .subscribeOn(scheduler)

    fun validateApiKey(key: String): Completable =
        api.validateApiKey(key)
            .doOnAfterError { settings.isProfileValidated = false }
            .flatMapCompletable { profile ->
                completableFromFunction {
                    with(settings) {
                        name = profile.name
                        avatar = profile.avatar
                        isPremium = profile.isPremium
                        isSupporter = profile.isSupporter
                        isProfileValidated = true
                        apiKey = profile.key
                    }
                }
            }
            .subscribeOn(scheduler)
}
