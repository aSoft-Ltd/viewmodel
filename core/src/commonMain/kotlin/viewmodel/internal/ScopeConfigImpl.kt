package viewmodel.internal

import koncurrent.Executor
import logging.Logger
import viewmodel.ScopeConfig
import viewmodel.StatefulViewModelConfig

internal class ScopeConfigImpl<out A>(
    override val api: A,
    override val executor: Executor,
    override val logger: Logger
) : AbstractViewModelConfig(), ScopeConfig<A> {
    override fun <R> map(transformer: (A) -> R): ScopeConfig<R> = ScopeConfigImpl(
        transformer(api), executor, logger
    )
}