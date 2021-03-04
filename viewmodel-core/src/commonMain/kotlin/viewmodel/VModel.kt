package viewmodel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import logging.logger

abstract class VModel<in I, S>(initialState: S) : PlatformVModel() {
    internal val logger = logger(this::class.simpleName ?: "Anonymous ViewModel")
    val ui = MutableStateFlow(initialState)
    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    init {
        coroutineScope.launch {
            ui.collectIndexed { index, value ->
                log("State ${if (index == 0) "begins at" else "changed to"} ${value?.toDetailedString}")
            }
        }
    }

    /**
     * Will execute the provided intent on the viewmodel's scope
     */
    open fun post(i: I) {
        log(i)
        coroutineScope.execute(i)
    }

    /**
     * Will execute the provided intent on the calling scope
     */
    open fun CoroutineScope.start(i: I) {
        log(i)
        execute(i)
    }

    abstract fun CoroutineScope.execute(i: I): Any
}