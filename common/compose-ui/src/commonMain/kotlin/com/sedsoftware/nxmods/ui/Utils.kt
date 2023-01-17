package com.sedsoftware.nxmods.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.badoo.reaktive.disposable.scope.DisposableScope

@Composable
fun DisposableScope(key: Any, block: DisposableScope.() -> Unit) {
    DisposableEffect(key) {
        val scope = DisposableScope()
        scope.block()
        onDispose(scope::dispose)
    }
}
