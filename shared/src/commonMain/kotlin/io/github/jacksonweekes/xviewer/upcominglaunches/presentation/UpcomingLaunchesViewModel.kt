package io.github.jacksonweekes.xviewer.upcominglaunches.presentation

import io.github.jacksonweekes.xviewer.mvi.BaseViewModel
import io.github.jacksonweekes.xviewer.upcominglaunches.data.LaunchesRepository
import kotlinx.coroutines.launch

class UpcomingLaunchesViewModel(private val launchesRepository: LaunchesRepository, stateReducer: UpcomingLaunchesStateReducer) : BaseViewModel<
        UpcomingLaunchesViewState,
        UpcomingLaunchesIntent,
        UpcomingLaunchesEffect>(UpcomingLaunchesViewState(), stateReducer) {

    init {
        viewModelScope.launch {
            try {
                val launches = launchesRepository.getUpcomingLaunches()
                updateViewState(UpcomingLaunchesEffect.SetLaunches(launches))
            } catch (e: Exception) {
                updateViewState(UpcomingLaunchesEffect.ShowError)
            }
        }
    }

    override fun onIntent(intent: UpcomingLaunchesIntent) {
        TODO("Not yet implemented")
    }
}