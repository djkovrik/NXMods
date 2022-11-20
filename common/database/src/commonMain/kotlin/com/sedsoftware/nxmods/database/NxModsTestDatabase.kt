package com.sedsoftware.nxmods.database

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.completableFromFunction
import com.badoo.reaktive.completable.observeOn
import com.badoo.reaktive.maybe.Maybe
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.Scheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.subject.behavior.BehaviorSubject
import com.sedsoftware.nxmods.domain.entity.CachedModData
import com.sedsoftware.nxmods.domain.entity.EndorsementInfo
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.TrackingInfo
import com.sedsoftware.nxmods.domain.tools.NxModsDatabase

class NxModsTestDatabase(
    private val scheduler: Scheduler
) : NxModsDatabase {

    private val gameInfoSubject: BehaviorSubject<Map<Long, GameInfoEntity>> = BehaviorSubject(emptyMap())
    private val trackingInfoSubject: BehaviorSubject<Map<Long, TrackingInfoEntity>> = BehaviorSubject(emptyMap())
    private val endorsementInfoSubject: BehaviorSubject<Map<Long, EndorsementInfoEntity>> = BehaviorSubject(emptyMap())

    private val gameInfoObservable: Observable<Map<Long, GameInfoEntity>> = gameInfoSubject.observeOn(scheduler)
    private val trackingInfoObservable: Observable<Map<Long, TrackingInfoEntity>> = trackingInfoSubject.observeOn(scheduler)
    private val endorsementInfoObservable: Observable<Map<Long, EndorsementInfoEntity>> = endorsementInfoSubject.observeOn(scheduler)

    private val testing: Testing = Testing()

    override fun observeGamesList(): Observable<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun observeGame(domain: String): Observable<GameInfo> {
        TODO("Not yet implemented")
    }

    override fun observeBookmarkedGames(): Observable<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun bookmark(domain: String, isAdded: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun saveGames(items: List<GameInfo>): Completable {
        TODO("Not yet implemented")
    }

    override fun saveGame(item: GameInfo): Completable {
        TODO("Not yet implemented")
    }

    override fun searchByName(name: String): Maybe<List<GameInfo>> {
        TODO("Not yet implemented")
    }

    override fun saveTracked(items: List<TrackingInfo>): Completable {
        TODO("Not yet implemented")
    }

    override fun track(domain: String, id: Long, track: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun saveEndorsed(items: List<EndorsementInfo>): Completable {
        TODO("Not yet implemented")
    }

    override fun endorse(domain: String, id: Long, endorse: Boolean): Completable {
        TODO("Not yet implemented")
    }

    override fun getCachedModData(domain: String, id: Long): Single<CachedModData> {
        TODO("Not yet implemented")
    }

    private fun execute(block: () -> Unit): Completable =
        completableFromFunction(block)
            .observeOn(scheduler)

    inner class Testing {

        private fun updateGameInfo(func: (Map<Long, GameInfoEntity>) -> Map<Long, GameInfoEntity>) {
            gameInfoSubject.onNext(func(gameInfoSubject.value))
        }

        private fun updateTrackingInfo(func: (Map<Long, TrackingInfoEntity>) -> Map<Long, TrackingInfoEntity>) {
            trackingInfoSubject.onNext(func(trackingInfoSubject.value))
        }

        private fun updateEndorsementInfo(func: (Map<Long, EndorsementInfoEntity>) -> Map<Long, EndorsementInfoEntity>) {
            endorsementInfoSubject.onNext(func(endorsementInfoSubject.value))
        }
    }
}

/*
selectGame:
selectBookmarkedGame:
selectAllGames:
bookmarkGame:
insertGame:
searchGame:
insertTracked:
deleteTracked:
trackedExists:
insertEndorsed:
deleteEndorsed:
endorsedExists:
 */
