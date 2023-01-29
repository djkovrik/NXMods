package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.doOnAfterNext
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.subscribeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

internal class NxModsAuthManager(
    private val api: NxModsAuthApi,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getCurrentApiKey(): Single<String> =
        singleOf(settings.apiKey)
            .subscribeOn(scheduler)

    fun getCurrentDomain(): Single<String> =
        singleOf(settings.currentGameDomain)
            .subscribeOn(scheduler)

    fun validateApiKey(key: String): Observable<String> =
        api.validateApiKey(key)
            .doOnAfterNext { response ->
                with(settings) {
                    name = response.name
                    avatar = response.avatar
                    isPremium = response.isPremium
                    isSupporter = response.isSupporter
                    isProfileValidated = response.key.isNotEmpty()
                    apiKey = response.key
                }
            }
            .map { it.key }
            .subscribeOn(scheduler)
}
