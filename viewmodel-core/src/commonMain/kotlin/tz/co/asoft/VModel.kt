package tz.co.asoft

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed

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

    open fun post(i: I) {
        log(i)
        coroutineScope.execute(i)
    }

    abstract fun CoroutineScope.execute(i: I): Any
}