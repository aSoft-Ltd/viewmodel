@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package viewmodel

import kase.LazyState
import kase.Pending
import koncurrent.FailedLater
import koncurrent.Later
import kotlin.js.JsExport

abstract class LazyViewModel<S>(config: ScopeConfig<Any?>) : ViewModel<LazyState<S>>(config.of(Pending)) {

    protected fun <R> ifNotPending(block: () -> Later<R>): Later<R> {
        if (ui.value !is Pending) return mustBePending()
        return block()
    }

    open fun deInitialize() {
        ui.value = Pending
    }

    private companion object {
        const val MUST_BE_PENDING = "Scope must be in a pending state for it be initialized"
        inline fun mustBePending() = FailedLater(IllegalStateException(MUST_BE_PENDING))
    }
}