import CounterViewModel.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import logging.*
import org.junit.Before
import viewmodel.ViewModel
import test.asyncTest
import java.util.concurrent.Executors
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Give a [ViewModel] [CounterViewModel]
 */
class ViewModelIntentTest {

    init {
        Logging.init(ConsoleAppender())
    }

    private val mainDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(mainDispatcher)
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

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        mainDispatcher.close()
    }
}