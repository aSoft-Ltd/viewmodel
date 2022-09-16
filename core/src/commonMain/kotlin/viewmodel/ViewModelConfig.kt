@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package viewmodel

import cache.Cache
import cache.CacheMock
import koncurrent.Executor
import koncurrent.SynchronousExecutor
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json
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
    val codec: StringFormat
    val cache: Cache

    fun <S> of(state: S): StatefulViewModelConfig<S>

    companion object {
        @JvmField
        val DEFAULT_LOGGER = Logger(ConsoleAppender())

        @JvmField
        val DEFAULT_EXECUTOR = SynchronousExecutor

        @JvmField
        val DEFAULT_CODEC = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }

        @JvmField
        val DEFAULT_CACHE = CacheMock()

        @JsName("of")
        @JvmName("of")
        @JvmOverloads
        @JvmStatic
        operator fun invoke(
            executor: Executor = DEFAULT_EXECUTOR,
            logger: Logger = DEFAULT_LOGGER,
            codec: StringFormat = DEFAULT_CODEC,
            cache: Cache = DEFAULT_CACHE
        ): ViewModelConfig = StatefulViewModelConfigImpl(Unit, executor, logger, cache, codec)
    }
}