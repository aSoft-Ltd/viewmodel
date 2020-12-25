import CounterViewModel.Intent
import CounterViewModel.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tz.co.asoft.IntentBus
import tz.co.asoft.VModel

class CounterViewModel(val delay: Long) : VModel<Intent, State>(State(0)) {

    companion object : IntentBus<Intent>()

    data class State(val value: Int)

    init {
        launch { collect { post(it) } }
    }

    sealed class Intent {
        data class CountUp(val by: Int) : Intent()
        data class CountDown(val by: Int) : Intent()
    }

    val countState get() = ui.value.value

    override fun execute(i: Intent) = when (i) {
        is Intent.CountUp -> countUp(i)
        is Intent.CountDown -> countDown(i)
    }

    private fun countDown(i: Intent.CountDown) = launch {
        delay(delay)
        ui.value = State(value = ui.value.value - i.by)
    }

    private fun countUp(i: Intent.CountUp) = launch {
        delay(delay)
        ui.value = State(value = ui.value.value + i.by)
    }
}