@file:JvmName("ExpectBuilders")

package viewmodel

import kotlin.jvm.JvmName

fun <I, S, V : ViewModel<I, S>> expect(viewModel: V) = ViewModelExpectation(viewModel)

