package io.github.jacksonweekes.xviewer.android.launches

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.github.jacksonweekes.xviewer.upcominglaunches.data.Launch
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewModel
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewState

@Composable
fun UpcomingLaunchesScreen(viewModel: UpcomingLaunchesViewModel) {
    val uiState = viewModel.viewState.collectAsState()

    Content(uiState.value)
}

@Composable
fun Content(uiState: UpcomingLaunchesViewState) {
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = uiState.isLoading),
        onRefresh = { /*TODO*/ }) {
        if (uiState.launches.isNotEmpty()) {
            Success(launches = uiState.launches)
        }
        if (uiState.isError) {
            Error()
        }
    }
}

@Composable
fun Success(launches: List<Launch>) {
    LazyColumn {
        items(launches) { launch ->
            LaunchRow(launch)
        }
    }
}

@Composable
fun LaunchRow(launch: Launch) {
    Row {
        Text("${launch.dateLocal}: ${launch.name}")
    }
}

@Composable
fun Error() {
    Text("Something went wrong...")
}