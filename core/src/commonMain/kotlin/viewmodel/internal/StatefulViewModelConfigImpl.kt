package viewmodel.internal

import cache.Cache
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import viewmodel.StatefulViewModelConfig

internal class StatefulViewModelConfigImpl<out S>(
    override val state: S,
    override val executor: Executor,
    override val logger: Logger,
    override val cache: Cache,
    override val codec: StringFormat
) : AbstractViewModelConfig(), StatefulViewModelConfig<S>