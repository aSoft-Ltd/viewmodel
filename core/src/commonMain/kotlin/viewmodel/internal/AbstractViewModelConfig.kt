package viewmodel.internal

import viewmodel.StatefulViewModelConfig
import viewmodel.ViewModelConfig

abstract class AbstractViewModelConfig : ViewModelConfig {
    override fun <S> of(state: S) = StatefulViewModelConfig(
        state, executor, logger
    )
}