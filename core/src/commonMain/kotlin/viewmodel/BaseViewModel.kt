package viewmodel

import koncurrent.Executor
import logging.Logger

expect abstract class BaseViewModel(config: ViewModelConfig<*>) {

    protected val logger: Logger

    protected val executor: Executor

    protected fun log(msg: String)

    protected open fun onCleared()
}