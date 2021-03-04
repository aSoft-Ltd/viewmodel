package viewmodel

import react.useMemo
import tz.co.asoft.asState
import kotlin.reflect.KProperty

fun <V : VModel<*, *>> useViewModel(builder: () -> V): V = useMemo(builder, arrayOf())

fun <V : VModel<*, *>> viewModel(builder: () -> V): V = useMemo(builder, arrayOf())

operator fun <S> VModel<*, S>.getValue(thisRef: Any?, property: KProperty<*>): S = ui.asState()
