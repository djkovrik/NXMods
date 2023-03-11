package com.sedsoftware.nxmods.component.auth.domain

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.andThen
import com.badoo.reaktive.completable.asSingle
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.observable.flatMapCompletable
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.singleOf
import com.badoo.reaktive.single.subscribeOn
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.tools.NxModsSettings

internal class NxModsAuthManager(
    private val api: NxModsAuthApi,
    private val db: NxModsAuthDb,
    private val settings: NxModsSettings,
    private val scheduler: Scheduler = ioScheduler
) {

    fun getCurrentApiKey(): Single<String> =
        singleOf(settings.apiKey)
            .subscribeOn(scheduler)

    fun getCurrentDomain(): Single<String> =
        singleOf(settings.currentGameDomain)
            .subscribeOn(scheduler)

    fun validateApiKey(key: String): Single<String> =
        api.validateApiKey(key)
            .flatMapCompletable(::storeOwnProfile)
            .andThen(fetchAllEndorsed(key))
            .andThen(fetchAllTracked(key))
            .asSingle { settings.apiKey }
            .subscribeOn(scheduler)

    private fun storeOwnProfile(profile: OwnProfile): Completable =
        completableFromFunction {
            with(settings) {
                name = profile.name
                avatar = profile.avatar
                isPremium = profile.isPremium
                isSupporter = profile.isSupporter
                isProfileValidated = profile.key.isNotEmpty()
                apiKey = profile.key
            }
        }

    private fun fetchAllEndorsed(key: String): Completable =
        api.getEndorsed(key)
            .flatMapCompletable(db::saveEndorsed)

    private fun fetchAllTracked(key: String): Completable =
        api.getTracked(key)
            .flatMapCompletable(db::saveTracked)
}
