@file:JvmName("ExpectBuilders")

package viewmodel

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import kotlin.jvm.JvmName

fun <S, V : ViewModel<S>> expect(viewModel: V) = live.expect(viewModel.ui)

inline fun <S, V : ViewModel<S>> V.expect(
    executor: Executor = SynchronousExecutor,
    builder: V.() -> Unit
) = live.expect(ui)