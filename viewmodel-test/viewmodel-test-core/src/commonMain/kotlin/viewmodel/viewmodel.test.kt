package viewmodel

import kotlinx.coroutines.coroutineScope
import kotlin.test.assertEquals

suspend inline fun <I, S> ViewModel<I, S>.test(intent: I) = coroutineScope { start(intent) }

suspend fun <I, S> ViewModel<I, S>.test(intent: I, toHave: S) {
    test(intent)
    assertEquals(toHave, state.value, "Expected ${this::class.simpleName} State to be $toHave but was ${state.value}")
}