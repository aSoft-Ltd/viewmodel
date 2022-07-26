@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package viewmodel

import live.*
import viewmodel.internal.toDetailedString
import kotlin.js.JsExport

abstract class ViewModel<out S>(private val config: StatefulViewModelConfig<S>) : BaseViewModel(config) {

    val ui: MutableLive<@UnsafeVariance S> = mutableLiveOf(config.state)

    init {
        ui.watch(WatchMode.Eagerly) { log("State at ${it?.toDetailedString}") }
    }

    override fun onCleared() {
        ui.stopAll()
    }
}