package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import io.github.jacksonweekes.xviewer.mvi.Intent

sealed class UpcomingLaunchesIntent: Intent {
    object Refresh: UpcomingLaunchesIntent()
}