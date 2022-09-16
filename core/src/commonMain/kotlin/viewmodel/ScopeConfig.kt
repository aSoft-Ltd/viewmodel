@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package viewmodel

import cache.Cache
import koncurrent.Executor
import koncurrent.SynchronousExecutor
import kotlinx.serialization.StringFormat
import logging.ConsoleAppender
import logging.Logger
import viewmodel.internal.ScopeConfigImpl
import viewmodel.internal.StatefulViewModelConfigImpl
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.*

@JsExport
interface ScopeConfig<out A> : ViewModelConfig {
    val api: A

    fun <R> map(transformer: (A) -> R): ScopeConfig<R>

    companion object {

        @JsName("ofService")
        @JvmName("ofService")
        @JvmOverloads
        @JvmStatic
        operator fun <A> invoke(
            api: A,
            executor: Executor = ViewModelConfig.DEFAULT_EXECUTOR,
            logger: Logger = ViewModelConfig.DEFAULT_LOGGER,
            cache: Cache = ViewModelConfig.DEFAULT_CACHE,
            codec: StringFormat = ViewModelConfig.DEFAULT_CODEC
        ): ScopeConfig<A> = ScopeConfigImpl(api, executor, logger, cache, codec)
    }
}