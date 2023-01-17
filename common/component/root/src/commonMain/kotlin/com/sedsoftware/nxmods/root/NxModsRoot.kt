package com.sedsoftware.nxmods.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.badoo.reaktive.subject.publish.PublishSubject
import com.sedsoftware.nxmods.component.auth.NxModsAuth

interface NxModsRoot {

    val childStack: Value<ChildStack<*, Child>>

    val messages: PublishSubject<String>

    sealed class Child {
        data class Auth(val component: NxModsAuth) : Child()
    }
}
