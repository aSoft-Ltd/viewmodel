@file:JsExport

package viewmodel

import kotlin.js.JsExport

interface ScopeConfig<out A> : ViewModelConfig {
    val api: A
    fun <R> map(transformer: (A) -> R): ScopeConfig<R>
}