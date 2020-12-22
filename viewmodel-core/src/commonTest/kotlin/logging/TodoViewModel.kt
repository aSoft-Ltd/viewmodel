@file:Suppress("PackageDirectoryMismatch")

import TodoViewModel.Intent
import TodoViewModel.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tz.co.asoft.IntentBus
import tz.co.asoft.VModel

class TodoViewModel : VModel<Intent, State>(State.Init) {

    sealed class State {
        object Init : State()
        data class ShowTodo(val todo: Todo) : State()
    }

    sealed class Intent {
        data class ViewTodo(val todo: Todo) : Intent()
        object ReInit : Intent()
    }

    override fun execute(i: Intent): Any = launch {
        delay(10)
        ui.value = when (i) {
            is Intent.ViewTodo -> State.ShowTodo(i.todo)
            Intent.ReInit -> State.Init
        }
    }
}