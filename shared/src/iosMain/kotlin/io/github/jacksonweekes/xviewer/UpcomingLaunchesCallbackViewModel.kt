package io.github.jacksonweekes.xviewer

import co.touchlab.kermit.Logger
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesEffect
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesIntent
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewModel
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewState

class UpcomingLaunchesCallbackViewModel(override val viewModel: UpcomingLaunchesViewModel, logger: Logger) :
    BaseCallbackViewModel<UpcomingLaunchesViewState, UpcomingLaunchesIntent, UpcomingLaunchesEffect>(logger)