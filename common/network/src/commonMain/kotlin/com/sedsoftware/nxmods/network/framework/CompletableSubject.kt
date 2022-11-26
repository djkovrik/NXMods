package com.sedsoftware.nxmods.network.framework

import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.completable.CompletableCallbacks
import com.badoo.reaktive.observable.asCompletable
import com.badoo.reaktive.subject.Subject
import com.badoo.reaktive.subject.publish.PublishSubject

class CompletableSubject private constructor(
    private val subject: Subject<Nothing>
) : Completable by subject.asCompletable(), CompletableCallbacks by subject {
    constructor() : this(PublishSubject())
}
