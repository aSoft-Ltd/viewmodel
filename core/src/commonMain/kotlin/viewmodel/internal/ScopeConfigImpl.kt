package viewmodel.internal

import cache.Cache
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import viewmodel.ScopeConfig
import viewmodel.StatefulViewModelConfig

internal class ScopeConfigImpl<out A>(
    override val api: A,
    override val executor: Executor,
    override val logger: Logger,
    override val cache: Cache,
    override val codec: StringFormat
) : AbstractViewModelConfig(), ScopeConfig<A> {
    override fun <R> map(transformer: (A) -> R): ScopeConfig<R> = ScopeConfigImpl(
        transformer(api), executor, logger, cache, codec
    )
}