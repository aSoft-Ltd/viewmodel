@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package viewmodel

import kase.LazyState
import kase.Pending
import kotlin.js.JsExport

abstract class LazyViewModel<S>(config: ScopeConfig<Any?>) : ViewModel<LazyState<S>>(config.of(Pending))