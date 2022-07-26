package viewmodel.internal

import koncurrent.Executor
import logging.Logger
import viewmodel.StatefulViewModelConfig

internal class StatefulViewModelConfigImpl<out S>(
    override val state: S,
    override val executor: Executor,
    override val logger: Logger
) : AbstractViewModelConfig(), StatefulViewModelConfig<S>