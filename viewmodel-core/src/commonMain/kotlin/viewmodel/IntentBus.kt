package viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

open class IntentBus<I> {
    private val INTENT_BUS = MutableSharedFlow<I>(replay = 0)
    val coroutineScope = CoroutineScope(SupervisorJob())

    fun post(i: I) {
        coroutineScope.launch {
            INTENT_BUS.emit(i)
        }
    }

    open suspend fun collect(collector: suspend (I) -> Unit) {
        INTENT_BUS.collect(collector)
    }

    fun VModel<I, *>.observeIntentBus() = coroutineScope.launch { collect { post(it) } }
}