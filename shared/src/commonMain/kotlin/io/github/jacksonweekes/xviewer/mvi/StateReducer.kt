package io.github.jacksonweekes.xviewer.mvi

abstract class StateReducer<VS: ViewState, E: Effect> {
    abstract fun reduce(viewState: VS, effect: E): VS
}