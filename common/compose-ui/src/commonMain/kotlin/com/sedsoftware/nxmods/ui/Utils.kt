package com.sedsoftware.nxmods.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimator
import com.badoo.reaktive.disposable.scope.DisposableScope
import com.sedsoftware.nxmods.domain.type.ModListType

@Composable
fun DisposableScope(key: Any, block: DisposableScope.() -> Unit) {
    DisposableEffect(key) {
        val scope = DisposableScope()
        scope.block()
        onDispose(scope::dispose)
    }
}

internal fun StackAnimator.flipSide(): StackAnimator =
    StackAnimator { direction, onFinished, content ->
        invoke(
            direction = direction.flipSide(),
            onFinished = onFinished,
            content = content,
        )
    }

internal fun Direction.flipSide(): Direction =
    when (this) {
        Direction.ENTER_FRONT -> Direction.ENTER_BACK
        Direction.EXIT_FRONT -> Direction.EXIT_BACK
        Direction.ENTER_BACK -> Direction.ENTER_FRONT
        Direction.EXIT_BACK -> Direction.EXIT_FRONT
    }
