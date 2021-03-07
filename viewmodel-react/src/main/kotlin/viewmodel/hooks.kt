package viewmodel

import react.useMemo

fun <V : ViewModel<*, *>> useViewModel(builder: () -> V): V = useMemo(builder, arrayOf())

fun <V : ViewModel<*, *>> viewModel(builder: () -> V): V = useMemo(builder, arrayOf())
