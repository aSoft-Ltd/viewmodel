package tz.co.asoft

import kotlinx.coroutines.Job
import kotlin.test.assertEquals

suspend fun <I, S> VModel<I, S>.test(intent: I) {
    val job = execute(intent) as? Job
    job?.join()
}

suspend fun <I, S> VModel<I, S>.test(intent: I, toHave: S) {
    test(intent)
    assertEquals(toHave, ui.value, "Expected ${this::class.simpleName} State to be $toHave but was ${ui.value}")
}