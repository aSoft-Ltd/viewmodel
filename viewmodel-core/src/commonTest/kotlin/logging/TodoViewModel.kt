@file:Suppress("PackageDirectoryMismatch")

package logging

import logging.TodoViewModel.Intent
import logging.TodoViewModel.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viewmodel.ViewModel

class TodoViewModel : ViewModel<Intent, State>(State.Init) {

    sealed interface State {
        object Init : State
        data class ShowTodo(val todo: Todo) : State
    }

    sealed interface Intent {
        data class ViewTodo(val todo: Todo) : Intent
        object ReInit : Intent
    }

    override fun CoroutineScope.execute(i: Intent): Any = launch {
        delay(10)
        ui.value = when (i) {
            is Intent.ViewTodo -> State.ShowTodo(i.todo)
            Intent.ReInit -> State.Init
        }
    }
}