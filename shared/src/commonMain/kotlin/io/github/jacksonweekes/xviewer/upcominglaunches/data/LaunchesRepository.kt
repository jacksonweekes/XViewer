package io.github.jacksonweekes.xviewer.upcominglaunches.data

class LaunchesRepository(private val launchesApi: LaunchesApi) {
    suspend fun getUpcomingLaunches() = launchesApi.getUpcomingLaunches()
}