package tz.co.asoft

import viewmodel.VModel
import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun <S> Expectation<VModel<*, S>>.toBeIn(expectedState: S) = assertEquals(expectedState, value.ui.value)

inline fun <reified E> Expectation<VModel<*, *>>.toBeIn(): E {
    val state = value.ui.value
    assertTrue(
        """
            
        Expected State : ${E::class.simpleName}
        Actual State   : $state
        ==================================
        
        """.trimIndent()
    ) { state is E }
    return state as E
}

inline fun <reified L, reified R> Expectation<VModel<*, *>>.toBeInEither(): Either<L, R> {
    val state = value.ui.value
    assertTrue(
        """
    
    Expected States : [${L::class.simpleName} | ${R::class.simpleName}]
    Actual State    : $state
    =============================================================

    """.trimIndent()
    ) { state is L || state is R }
    return state.asEither()
}