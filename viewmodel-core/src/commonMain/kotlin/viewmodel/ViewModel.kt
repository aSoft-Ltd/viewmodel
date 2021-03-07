package viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import live.Live
import logging.logger

abstract class ViewModel<in I, S>(initialState: S) : PlatformViewModel() {
    internal val logger = logger(this::class.simpleName ?: "Anonymous ViewModel")
    val state = Live(initialState)
    open val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    init {
        state.watch { log("State at ${it?.toDetailedString}") }
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