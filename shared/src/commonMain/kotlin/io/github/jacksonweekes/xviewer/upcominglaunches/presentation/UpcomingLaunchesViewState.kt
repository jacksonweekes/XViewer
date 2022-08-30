package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import io.github.jacksonweekes.xviewer.mvi.ViewState
import io.github.jacksonweekes.xviewer.upcominglaunches.data.Launch

sealed class UpcomingLaunchesViewState: ViewState {
    object Loading: UpcomingLaunchesViewState()
    data class Success(val launches: List<Launch>): UpcomingLaunchesViewState()
    object Error: UpcomingLaunchesViewState()
}