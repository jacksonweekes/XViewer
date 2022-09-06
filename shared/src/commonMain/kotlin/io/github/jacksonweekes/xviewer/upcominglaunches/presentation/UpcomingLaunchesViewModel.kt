package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import co.touchlab.kermit.Logger
import io.github.jacksonweekes.xviewer.mvi.BaseViewModel
import io.github.jacksonweekes.xviewer.upcominglaunches.data.LaunchesRepository
import kotlinx.coroutines.launch

class UpcomingLaunchesViewModel(
    private val launchesRepository: LaunchesRepository,
    stateReducer: UpcomingLaunchesStateReducer,
    private val logger: Logger
) : BaseViewModel<
        UpcomingLaunchesViewState,
        UpcomingLaunchesIntent,
        UpcomingLaunchesEffect>(UpcomingLaunchesViewState(), stateReducer, logger) {

    init {
        loadLaunchData()
    }

    override fun onIntent(intent: UpcomingLaunchesIntent) = when (intent) {
        UpcomingLaunchesIntent.Refresh -> loadLaunchData()
    }

    private fun loadLaunchData() {
        viewModelScope.launch {
            try {
                val launches = launchesRepository.getUpcomingLaunches()
                updateViewState(UpcomingLaunchesEffect.SetLaunches(launches))
            } catch (e: Exception) {
                logger.d(e.message ?: "Something went wrong...")
                updateViewState(UpcomingLaunchesEffect.ShowError)
            }
        }
    }
}
