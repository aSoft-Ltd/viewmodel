@file:Suppress("PackageDirectoryMismatch")

import kotlinx.coroutines.delay
import logging.ConsoleAppender
import logging.Logging
import logging.TodoViewModel
import logging.TodoViewModel.Intent
import test.asyncTest
import kotlin.test.Test

class ViewModelLogging {

    init {
        Logging.init(ConsoleAppender())
    }

    @Test
    fun should_print_logging_output() = asyncTest {
        val vm = TodoViewModel()
        delay(50)
        vm.post(Intent.ReInit)
    }
}