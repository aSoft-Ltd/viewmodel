package tz.co.asoft

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

open class IntentBus<I> : CoroutineScope by CoroutineScope(SupervisorJob()) {
    private val INTENT_BUS = MutableSharedFlow<I>(replay = 0)

    fun post(i: I) = launch {
        INTENT_BUS.emit(i)
    }

    suspend fun collect(collector: suspend (I) -> Unit) {
        INTENT_BUS.collect(collector)
    }
}