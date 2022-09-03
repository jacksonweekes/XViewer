package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import io.github.jacksonweekes.xviewer.mvi.ViewState
import io.github.jacksonweekes.xviewer.upcominglaunches.data.Launch

data class UpcomingLaunchesViewState(
    val launches: List<Launch> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false): ViewState