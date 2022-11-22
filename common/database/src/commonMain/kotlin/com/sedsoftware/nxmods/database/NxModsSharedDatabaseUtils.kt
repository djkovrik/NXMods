package com.sedsoftware.nxmods.database

import com.badoo.reaktive.base.setCancellable
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.observable.Observable
import com.badoo.reaktive.observable.map
import com.badoo.reaktive.observable.observable
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.asCompletable
import com.badoo.reaktive.single.doOnBeforeSuccess
import com.badoo.reaktive.single.flatMapObservable
import com.badoo.reaktive.single.map
import com.badoo.reaktive.single.observeOn
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Query.Listener

internal fun <T : Any> NxModsSharedDatabase.query(query: (NexusDatabaseQueries) -> Query<T>): Single<Query<T>> =
    queries
        .observeOn(ioScheduler)
        .map(query)

internal fun NxModsSharedDatabase.execute(query: (NexusDatabaseQueries) -> Unit): Completable =
    queries
        .observeOn(ioScheduler)
        .doOnBeforeSuccess(query)
        .asCompletable()

internal fun <T : Any, R> Single<Query<T>>.observe(get: (Query<T>) -> R): Observable<R> =
    flatMapObservable { it.observed() }
        .observeOn(ioScheduler)
        .map(get)

internal fun <T : Any> Query<T>.observed(): Observable<Query<T>> =
    observable { emitter ->
        val listener =
            object : Listener {
                override fun queryResultsChanged() {
                    emitter.onNext(this@observed)
                }
            }

        emitter.onNext(this@observed)
        addListener(listener)
        emitter.setCancellable { removeListener(listener) }
    }
