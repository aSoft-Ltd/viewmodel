package viewmodel

fun <S> ViewModel<S>.asFlow() = ui.watchAsFlow()