package tz.co.asoft

import kotlinx.coroutines.coroutineScope
import kotlin.test.assertEquals

suspend inline fun <I, S> VModel<I, S>.test(intent: I) = coroutineScope { start(intent) }

suspend fun <I, S> VModel<I, S>.test(intent: I, toHave: S) {
    test(intent)
    assertEquals(toHave, ui.value, "Expected ${this::class.simpleName} State to be $toHave but was ${ui.value}")
}