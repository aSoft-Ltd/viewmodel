import CounterViewModel.Intent
import kotlinx.coroutines.delay
import tz.co.asoft.ConsoleAppender
import tz.co.asoft.Logging
import tz.co.asoft.VModel
import tz.co.asoft.asyncTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Give a [VModel] [CounterViewModel]
 */
class ViewModelIntentTest {

    init {
        Logging.init(ConsoleAppender())
    }

    @Test
    fun state_should_follow_intents() = asyncTest {
        val vm = CounterViewModel()
        assertEquals(0, vm.countState)
        delay(10)
        vm.post(Intent.CountUp(1))
        assertEquals(1, vm.countState)

        CounterViewModel.post(Intent.CountUp(2))
        delay(10)
        assertEquals(3, vm.countState)
    }
}