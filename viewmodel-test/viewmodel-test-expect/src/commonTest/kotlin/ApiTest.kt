import CounterViewModel.Intent
import CounterViewModel.State
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import test.asyncTest
import expect.*
import viewmodel.test
import viewmodel.toBeIn
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class ApiTest {

    @Test
    fun should_have_good_looking_api() = asyncTest {
        val vm = CounterViewModel(500)
        vm.test(Intent.CountUp(by = 1))
        expect(vm).toBeIn(State(1))

        vm.test(Intent.CountDown(by = 2))
        expect(vm).toBeIn(State(-1))

        vm.test(Intent.CountDown(by = -1))
        expect(vm).toBeIn(State(0))
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun should_take_1500_milli_seconds_to_build() = asyncTest {
        val vm = CounterViewModel(500)
        val time = measureTime {
            vm.test(Intent.CountUp(by = 1))
            expect(vm).toBeIn(State(1))

            vm.test(Intent.CountDown(by = 2))
            expect(vm).toBeIn(State(-1))

            vm.test(Intent.CountDown(by = -1))
            expect(vm).toBeIn(State(0))
        }
        println("Took ${time.inMilliseconds} milli seconds")
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun should_take_500_second_to_build() = asyncTest {
        val vm = CounterViewModel(500)
        val time = measureTime {
            coroutineScope {
                launch { vm.test(Intent.CountUp(by = 1)) }

                launch { vm.test(Intent.CountDown(by = 2)) }

                launch { vm.test(Intent.CountDown(by = -1)) }
            }
            expect(vm).toBeIn(State(0))
        }
        println("Took ${time.inMilliseconds} milli seconds")
    }
}