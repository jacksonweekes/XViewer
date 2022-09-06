package io.github.jacksonweekes.xviewer.upcominglaunches.data

import co.touchlab.kermit.Logger
import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LaunchesRepository(
    private val launchesApi: LaunchesApi,
    private val settings: Settings,
    private val clock: Clock,
    private val logger: Logger
) {

    suspend fun getUpcomingLaunches(): List<Launch> {
        val lastResponseTime = settings.getLong(TIMESTAMP_KEY, 0)
        val currentEpochMilliseconds = clock.now().toEpochMilliseconds()
        val cachedResponse = settings.getStringOrNull(RESPONSE_KEY)?.let {
            Json.decodeFromString<List<Launch>>(it)
        }
        logger.v("Last response time: $lastResponseTime")
        logger.v("Cached response: $cachedResponse")

        return if (cachedResponse == null || (currentEpochMilliseconds - CACHE_EXPIRY_MILLISECONDS) > lastResponseTime) {
            // Cache expired
            launchesApi.getUpcomingLaunches()
                .also { upcomingLaunches ->
                    settings.putString(
                        RESPONSE_KEY, Json.encodeToString(upcomingLaunches)
                    )
                    settings.putLong(TIMESTAMP_KEY, currentEpochMilliseconds)
                }
        } else {
            logger.v("Returning cached response")
            cachedResponse
        }
    }

//    {
//
//        launchesApi.getUpcomingLaunches()
//    }

    companion object {
        const val CACHE_EXPIRY_MILLISECONDS = 60000 // 60 sec * 1000 ms
        const val TIMESTAMP_KEY = "response_timestamp"
        const val RESPONSE_KEY = "response"
    }
}