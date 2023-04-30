package com.sedsoftware.nxmods.utils

import com.badoo.reaktive.base.Consumer

@Suppress("FunctionName")
inline fun <T> Consumer(crossinline block: (T) -> Unit): Consumer<T> =
    object : Consumer<T> {
        override fun onNext(value: T) {
            block(value)
        }
    }
