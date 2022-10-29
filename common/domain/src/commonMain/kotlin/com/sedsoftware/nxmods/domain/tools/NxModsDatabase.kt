package com.sedsoftware.nxmods.domain.tools

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.maybe.Maybe
import com.sedsoftware.nxmods.domain.entity.GameInfo
import com.sedsoftware.nxmods.domain.entity.GivenEndorsement
import com.sedsoftware.nxmods.domain.entity.TrackedMod

interface NxModsDatabase {
    fun saveGame(item: GameInfo): Completable
    fun saveGames(items: List<GameInfo>): Completable
    fun searchByName(name: String): Maybe<List<GameInfo>>
    fun getByDomain(domain: String): Maybe<GameInfo>
    fun saveTracked(items: List<TrackedMod>): Completable
    fun track(item: TrackedMod): Completable
    fun untrack(item: TrackedMod): Completable
    fun saveEndorsed(items: List<GivenEndorsement>): Completable
    fun endorse(id: Long, domain: String): Completable
    fun unendorse(id: Long, domain: String): Completable
}