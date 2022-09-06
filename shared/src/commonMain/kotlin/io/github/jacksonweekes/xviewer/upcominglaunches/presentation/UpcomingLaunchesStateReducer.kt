package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import io.github.jacksonweekes.xviewer.mvi.StateReducer
import io.github.jacksonweekes.xviewer.mvi.ViewState

class UpcomingLaunchesStateReducer: StateReducer<UpcomingLaunchesViewState, UpcomingLaunchesEffect>() {
    override fun reduce(viewState: UpcomingLaunchesViewState, effect: UpcomingLaunchesEffect): UpcomingLaunchesViewState =
        when (effect) {
            is UpcomingLaunchesEffect.SetLaunches -> UpcomingLaunchesViewState(effect.launches, isLoading = false, isError = false)
            is UpcomingLaunchesEffect.ShowError -> UpcomingLaunchesViewState(isLoading = false, isError = true)
        }
}

//
//        when (viewState) {
//            is UpcomingLaunchesViewState.Loading -> reduceLoadingViewState(effect)
//            is UpcomingLaunchesViewState.Success -> reduceSuccessViewState(effect)
//            is UpcomingLaunchesViewState.Error -> reduceErrorViewState(effect)
//        }
//
//    private fun reduceLoadingViewState(effect: UpcomingLaunchesEffect) = when (effect) {
//        is UpcomingLaunchesEffect.SetLaunches -> UpcomingLaunchesViewState.Success(effect.launches)
//        is UpcomingLaunchesEffect.ShowError -> UpcomingLaunchesViewState.Error
//    }
//
//    private fun reduceSuccessViewState(effect: UpcomingLaunchesEffect) = when (effect) {
//        is UpcomingLaunchesEffect.SetLaunches -> UpcomingLaunchesViewState.Success(effect.launches)
//        is UpcomingLaunchesEffect.ShowError -> UpcomingLaunchesViewState.Error
//    }
//
//    private fun reduceErrorViewState(effect: UpcomingLaunchesEffect) = when (effect) {
//        is UpcomingLaunchesEffect.SetLaunches -> UpcomingLaunchesViewState.Success(effect.launches)
//        is UpcomingLaunchesEffect.ShowError -> UpcomingLaunchesViewState.Error
//    }
//}