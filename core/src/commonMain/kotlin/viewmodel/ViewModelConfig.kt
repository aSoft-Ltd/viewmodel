@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package viewmodel

import koncurrent.Executor
import koncurrent.SynchronousExecutor
import logging.ConsoleAppender
import logging.Logger
import viewmodel.internal.StatefulViewModelConfigImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.*

@JsExport
interface ViewModelConfig {
    val executor: Executor
    val logger: Logger

    fun <S> of(state: S): StatefulViewModelConfig<S>

    companion object {
        @JvmField
        val DEFAULT_LOGGER = Logger(ConsoleAppender())

        @JvmField
        val DEFAULT_EXECUTOR = SynchronousExecutor


        @JsName("of")
        @JvmName("of")
        @JvmOverloads
        @JvmStatic
        operator fun invoke(
            executor: Executor = DEFAULT_EXECUTOR,
            logger: Logger = DEFAULT_LOGGER
        ): ViewModelConfig = StatefulViewModelConfig(Unit, executor, logger)
    }
}