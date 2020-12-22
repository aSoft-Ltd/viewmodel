@file:Suppress("PackageDirectoryMismatch")

import TodoViewModel.Intent
import kotlinx.coroutines.delay
import tz.co.asoft.ConsoleAppender
import tz.co.asoft.Logging
import tz.co.asoft.asyncTest
import kotlin.test.Test

class ViewModelLogging {

    init {
        Logging.init(ConsoleAppender())
    }

    @Test
    fun should_print_logging_output() = asyncTest{
        val vm = TodoViewModel()
        delay(50)
        vm.post(Intent.ReInit)
    }
}