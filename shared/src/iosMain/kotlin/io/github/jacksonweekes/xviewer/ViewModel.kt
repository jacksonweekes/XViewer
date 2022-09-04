package io.github.jacksonweekes.xviewer

import co.touchlab.kermit.Logger
import io.github.jacksonweekes.xviewer.mvi.BaseViewModel
import io.github.jacksonweekes.xviewer.mvi.Effect
import io.github.jacksonweekes.xviewer.mvi.Intent
import io.github.jacksonweekes.xviewer.mvi.ViewState
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow

/**
 * Base class that provides a Kotlin/Native equivalent to the AndroidX `ViewModel`. In particular, this provides
 * a [CoroutineScope][kotlinx.coroutines.CoroutineScope] which uses [Dispatchers.Main][kotlinx.coroutines.Dispatchers.Main]
 * and can be tied into an arbitrary lifecycle by calling [clear] at the appropriate time.
 * Borrowed from KaMPKit
 */
actual abstract class ViewModel {

    actual val viewModelScope = MainScope()

    /**
     * Override this to do any cleanup immediately before the internal [CoroutineScope][kotlinx.coroutines.CoroutineScope]
     * is cancelled in [clear]
     */
    protected actual open fun onCleared() {
    }

    /**
     * Cancels the internal [CoroutineScope][kotlinx.coroutines.CoroutineScope]. After this is called, the ViewModel should
     * no longer be used.
     */
    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}

abstract class BaseCallbackViewModel<VS: ViewState, I: Intent, E: Effect>(private val logger: Logger) {
    protected abstract val viewModel: BaseViewModel<VS, I, E>

    val viewState: FlowAdapter<VS>
    get() {
        logger.v("Getting viewState FlowAdapter")
        return viewModel.viewState.asCallbacks()
    }

    @Suppress("Unused") // Called from Swift
    fun onIntent(intent: I) = viewModel.onIntent(intent)

    @Suppress("Unused") // Called from Swift
    fun clear() = (viewModel as ViewModel).clear()

    /**
     * Create a [FlowAdapter] from this [Flow] to make it easier to interact with from Swift.
     */
    private fun <T : Any> Flow<T>.asCallbacks() =
        FlowAdapter(viewModel.viewModelScope, this)
}
