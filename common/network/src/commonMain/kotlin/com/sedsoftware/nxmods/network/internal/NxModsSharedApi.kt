package com.sedsoftware.nxmods.network.internal

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.asObservable
import com.sedsoftware.nxmods.domain.entity.ChangelogItem
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.ModInfo
import com.sedsoftware.nxmods.domain.entity.OwnProfile
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsApi
import com.sedsoftware.nxmods.domain.tools.NxModsSettings
import com.sedsoftware.nxmods.network.mappers.ChangelogMapper.changelogToDomain
import com.sedsoftware.nxmods.network.mappers.EndorsementInfoMapper.endorsementInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.GameInfoModelMapper.gameInfoModelListToDomain
import com.sedsoftware.nxmods.network.mappers.GameInfoModelMapper.gameInfoModelToDomain
import com.sedsoftware.nxmods.network.mappers.ModInfoModelMapper.modInfoListToDomain
import com.sedsoftware.nxmods.network.mappers.ModInfoModelMapper.modInfoToDomain
import com.sedsoftware.nxmods.network.mappers.OwnProfileMapper.profileToDomain
import com.sedsoftware.nxmods.network.mappers.TrackingInfoMapper.trackingInfoListToDomain
import com.sedsoftware.nxmods.network.models.EndorsementInfoModel
import com.sedsoftware.nxmods.network.models.GameInfoModel
import com.sedsoftware.nxmods.network.models.ModInfoModel
import com.sedsoftware.nxmods.network.models.OwnProfileModel
import com.sedsoftware.nxmods.network.models.TrackingInfoModel
import com.sedsoftware.nxmods.network.models.request.EndorseRequestBody
import com.sedsoftware.nxmods.network.models.request.TrackRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class NxModsSharedApi(
    private val settings: NxModsSettings
) : NxModsApi {

    internal val apiKey: String
        get() = settings.apiKey

    internal val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }
    }

    override fun getGames(): Observable<List<GameInfo>> =
        doGet<List<GameInfoModel>>("$BASE_URL/v1/games.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(gameInfoModelListToDomain)

    override fun getGame(domain: String): Observable<GameInfo> =
        doGet<GameInfoModel>("$BASE_URL/v1/games/${domain}.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(gameInfoModelToDomain)

    override fun getLatestAdded(domain: String): Observable<List<ModInfo>> =
        doGet<List<ModInfoModel>>("$BASE_URL/v1/games/${domain}/mods/latest_added.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(modInfoListToDomain)

    override fun getLatestUpdated(domain: String): Observable<List<ModInfo>> =
        doGet<List<ModInfoModel>>("$BASE_URL/v1/games/${domain}/mods/latest_updated.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(modInfoListToDomain)

    override fun getTrending(domain: String): Observable<List<ModInfo>> =
        doGet<List<ModInfoModel>>("$BASE_URL/v1/games/${domain}/mods/trending.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(modInfoListToDomain)

    override fun getMod(domain: String, id: Long): Observable<ModInfo> =
        doGet<ModInfoModel>("$BASE_URL/v1/games/${domain}/mods/${id}.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(modInfoToDomain)

    override fun getChangelog(domain: String, id: Long): Observable<List<ChangelogItem>> =
        doGet<Map<String, List<String>>>("$BASE_URL/v1/games/${domain}/mods/${id}/changelogs.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(changelogToDomain)

    override fun validateApiKey(key: String): Observable<OwnProfile> =
        doGet<OwnProfileModel>("$BASE_URL/v1/users/validate.json", key)
            .asObservable()
            .observeOn(ioScheduler)
            .map(profileToDomain)

    override fun getTracked(): Observable<List<TrackingInfo>> =
        doGet<List<TrackingInfoModel>>("$BASE_URL/v1/user/tracked_mods.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(trackingInfoListToDomain)

    override fun track(domain: String, id: Long): Completable =
        doPost("$BASE_URL/v1/user/tracked_mods.json", TrackRequestBody(domain, id))
            .observeOn(ioScheduler)

    override fun untrack(domain: String, id: Long): Completable =
        doDelete("$BASE_URL/v1/user/tracked_mods.json", TrackRequestBody(domain, id))
            .observeOn(ioScheduler)

    override fun getEndorsed(): Observable<List<EndorsementInfo>> =
        doGet<List<EndorsementInfoModel>>("$BASE_URL/v1/user/endorsements.json")
            .asObservable()
            .observeOn(ioScheduler)
            .map(endorsementInfoListToDomain)

    override fun endorse(domain: String, id: Long, version: String): Completable =
        doPost("$BASE_URL/v1/games/${domain}/mods/${id}/endorse.json", EndorseRequestBody(version))
            .observeOn(ioScheduler)

    override fun unendorse(domain: String, id: Long, version: String): Completable =
        doPost("$BASE_URL/v1/games/${domain}/mods/${id}/abstain.json", EndorseRequestBody(version))
            .observeOn(ioScheduler)

    internal companion object {
        const val BASE_URL = "https://api.nexusmods.com"
        const val API_KEY_HEADER = "apiKey"
        const val USER_AGENT = "NxMods"
    }
}
