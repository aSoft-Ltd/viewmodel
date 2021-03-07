import CounterViewModel.Intent
import CounterViewModel.State
import kotlinx.coroutines.CoroutineScope
import viewmodel.IntentBus
import viewmodel.ViewModel

class CounterViewModel : ViewModel<Intent, State>(State(0)) {

    companion object : IntentBus<Intent>()

    data class State(val value: Int)

    init {
        observeIntentBus()
    }

    sealed class Intent {
        data class CountUp(val by: Int) : Intent()
        data class CountDown(val by: Int) : Intent()
    }

    val countState get() = state.value.value

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        is Intent.CountUp -> state.value = State(value = state.value.value + i.by)
        is Intent.CountDown -> state.value = State(value = state.value.value - i.by)
    }
}