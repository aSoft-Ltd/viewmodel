import CounterViewModel.Intent
import CounterViewModel.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viewmodel.IntentBus
import viewmodel.ViewModel

class CounterViewModel(val delay: Long) : ViewModel<Intent, State>(State(0)) {

    companion object : IntentBus<Intent>()

    data class State(val value: Int)

    init {
        observeIntentBus()
    }

    sealed class Intent {
        data class CountUp(val by: Int) : Intent()
        data class CountDown(val by: Int) : Intent()
    }

    val countState get() = ui.value.value

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        is Intent.CountUp -> countUp(i)
        is Intent.CountDown -> countDown(i)
    }

    private fun CoroutineScope.countDown(i: Intent.CountDown) = launch {
        delay(delay)
        ui.value = State(value = ui.value.value - i.by)
    }

    private fun CoroutineScope.countUp(i: Intent.CountUp) = launch {
        delay(delay)
        ui.value = State(value = ui.value.value + i.by)
    }
}