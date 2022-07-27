@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package viewmodel

import koncurrent.Executor
import logging.Logger
import viewmodel.internal.StatefulViewModelConfigImpl
import kotlin.js.JsExport
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

@JsExport
interface StatefulViewModelConfig<out S> : ViewModelConfig {
    val state: S

    companion object {
        @JvmStatic
        @JvmName("create")
        @JvmOverloads
        operator fun <S> invoke(
            state: S,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER
        ): StatefulViewModelConfig<S> = StatefulViewModelConfigImpl(state, executor, logger)
    }
}