@file:JvmName("ExpectBuilders")

package viewmodel

import kotlinx.atomic.collections.mutableAtomicListOf
import kotlinx.coroutines.coroutineScope
import kotlin.jvm.JvmName

fun <I, S, V : ViewModel<I, S>> expect(viewModel: V) = ViewModelExpectation(viewModel)

suspend fun <I, S> ViewModel<I, S>.test(intent: I): ViewModelStateExpectation<S> {
    val states = mutableAtomicListOf<S>()
    val watcher = ui.watch { states.add(it) }
    coroutineScope { start(intent) }
    watcher.stop()
    return ViewModelStateExpectation(states.takeLast(states.size - 1))
}