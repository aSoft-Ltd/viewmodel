package tz.co.asoft

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

abstract class VModel<in I, S>(initialState: S) : PlatformVModel(), CoroutineScope by CoroutineScope(SupervisorJob() + Dispatchers.Default) {
    val logger = logger(this::class.simpleName ?: "Anonymous ViewModel")
    val ui = MutableStateFlow(initialState)

    init {
        launch {
            ui.collectIndexed { index, value ->
                log("State ${if (index == 0) "begins at" else "changed to"} ${value?.toDetailedString}")
            }
        }
    }

    open fun post(i: I) {
        logger.info("Sending Intent ${i?.toDetailedString}")
        execute(i)
    }

    abstract fun execute(i: I): Any
}