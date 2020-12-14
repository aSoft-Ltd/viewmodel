import CounterViewModel.Intent
import CounterViewModel.State
import kotlinx.coroutines.launch
import tz.co.asoft.IntentBus
import tz.co.asoft.VModel

class CounterViewModel : VModel<Intent, State>(State(0)) {

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
        is Intent.CountUp -> ui.value = State(value = ui.value.value + i.by)
        is Intent.CountDown -> ui.value = State(value = ui.value.value - i.by)
    }
}