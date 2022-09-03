package io.github.jacksonweekes.xviewer.upcominglaunches.data

import co.touchlab.kermit.Logger as KermitLogger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class LaunchesApi(engine: HttpClientEngine, log: KermitLogger) {

    private val client = HttpClient(engine) {
        //expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    log.v { message }
                }
            }

            level = LogLevel.INFO
        }
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

    suspend fun getUpcomingLaunches(): List<Launch> =
        client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.spacexdata.com/v4"
                encodedPath = "launches/upcoming"
            }
        }.body()
}