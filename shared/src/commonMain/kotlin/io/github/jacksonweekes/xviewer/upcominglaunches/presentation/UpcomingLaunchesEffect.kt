package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import io.github.jacksonweekes.xviewer.mvi.Effect
import io.github.jacksonweekes.xviewer.upcominglaunches.data.Launch

sealed class UpcomingLaunchesEffect: Effect {
    data class SetLaunches(val launches: List<Launch>): UpcomingLaunchesEffect()
    object ShowError: UpcomingLaunchesEffect()
}