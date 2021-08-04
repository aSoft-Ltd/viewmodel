import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import test.asyncTest
import viewmodel.CounterViewModel
import viewmodel.CounterViewModel.Intent
import viewmodel.CounterViewModel.State
import viewmodel.expect
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class ApiTest {

    val testScope = CoroutineScope(SupervisorJob())

    @Test
    fun should_capture_intermediate_states_too() = asyncTest {
        val vm = CounterViewModel(500, testScope)
        vm.expect(Intent.CountUp(by = 1)).toGoThrough(State(1))

        vm.expect(Intent.CountDown(by = 2)).toGoThrough(State(0), State(-1))

        vm.expect(Intent.CountDown(by = -1)).toGoThrough(State(0))

        vm.expect(Intent.CountUp(by = 0)).toGoThrough()
    }

    @Test
    fun should_have_good_looking_api() = asyncTest {
        val vm = CounterViewModel(500, testScope)
        vm.expect(Intent.CountUp(by = 1)).toBeIn(State(1))

        vm.expect(Intent.CountDown(by = 2)).toBeIn(State(-1))

        vm.expect(Intent.CountDown(by = -1)).toBeIn(State(0))
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun should_take_1500_milli_seconds_to_build() = asyncTest {
        val vm = CounterViewModel(500, testScope)
        val time = measureTime {
            vm.expect(Intent.CountUp(by = 1)).toBeIn(State(1))
            vm.expect(Intent.CountDown(by = 2)).toBeIn(State(-1))
            vm.expect(Intent.CountDown(by = -1)).toBeIn(State(0))
        }
        println("Took ${time.inWholeMilliseconds} milli seconds")
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun should_take_500_second_to_build() = asyncTest {
        val vm = CounterViewModel(500, testScope)
        val time = measureTime {
            coroutineScope {
                launch { vm.expect(Intent.CountUp(by = 1)) }

                launch { vm.expect(Intent.CountDown(by = 2)) }

                launch { vm.expect(Intent.CountDown(by = -1)) }
                expect(vm).toBeIn(State(0))
            }
        }
        println("Took ${time.inWholeMilliseconds} milli seconds")
    }
}