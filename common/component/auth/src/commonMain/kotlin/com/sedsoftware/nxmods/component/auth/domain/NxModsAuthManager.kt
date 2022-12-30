package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.doOnAfterError
import com.badoo.reaktive.observable.doOnAfterNext
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.component.auth.model.ApiKeyStatus
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

internal class NxModsAuthManager(
    private val api: NxModsAuthApi,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getCurrentApiKey(): Single<String> =
        singleOf(settings.apiKey)
            .subscribeOn(scheduler)

    fun validateApiKey(key: String): Observable<ApiKeyStatus> =
        api.validateApiKey(key)
            .doOnAfterError { settings.isProfileValidated = false }
            .doOnAfterNext { profile ->
                with(settings) {
                    name = profile.name
                    avatar = profile.avatar
                    isPremium = profile.isPremium
                    isSupporter = profile.isSupporter
                    isProfileValidated = true
                    apiKey = profile.key
                }
            }
            .map { ApiKeyStatus.VALID }
            .subscribeOn(scheduler)
}
