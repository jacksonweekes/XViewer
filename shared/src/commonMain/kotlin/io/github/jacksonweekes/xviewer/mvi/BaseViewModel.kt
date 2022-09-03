package io.github.jacksonweekes.xviewer.mvi

import io.github.jacksonweekes.xviewer.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<VS: ViewState, I: Intent, E: Effect>(initialState: VS, private val stateReducer: StateReducer<VS, E>): ViewModel() {

    private val _viewState = MutableStateFlow(initialState)
    val viewState: StateFlow<VS> = _viewState

//    private val effects = Channel<E>()
//
//    init {
//        effects.receiveAsFlow()
//            .onEach { effect ->
//                _viewState.update { viewState ->
//                    stateReducer.reduce(viewState, effect)
//                }
//            }
//    }

    abstract fun onIntent(intent: I)

    protected fun updateViewState(effect: E) {
        _viewState.update { viewState ->
            stateReducer.reduce(viewState, effect)
        }
        //effects.send(effect)
    }
}