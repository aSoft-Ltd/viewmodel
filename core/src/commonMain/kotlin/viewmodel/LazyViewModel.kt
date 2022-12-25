@file:JsExport

package viewmodel

import kase.LazyState
import kase.Pending
import kotlin.js.JsExport

abstract class LazyViewModel<S>(config: ScopeConfig<*>) : ViewModel<LazyState<S>>(config.of(Pending))