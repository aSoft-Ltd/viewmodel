@file:JsExport

package viewmodel

import kotlin.js.JsExport

interface StatefulViewModelConfig<out S> : ViewModelConfig {
    val state: S
}