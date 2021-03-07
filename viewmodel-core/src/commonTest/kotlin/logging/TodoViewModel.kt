@file:Suppress("PackageDirectoryMismatch")

import TodoViewModel.Intent
import TodoViewModel.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viewmodel.ViewModel

class TodoViewModel : ViewModel<Intent, State>(State.Init) {

    sealed class State {
        object Init : State()
        data class ShowTodo(val todo: Todo) : State()
    }

    sealed class Intent {
        data class ViewTodo(val todo: Todo) : Intent()
        object ReInit : Intent()
    }

    override fun CoroutineScope.execute(i: Intent): Any = launch {
        delay(10)
        state.value = when (i) {
            is Intent.ViewTodo -> State.ShowTodo(i.todo)
            Intent.ReInit -> State.Init
        }
    }
}