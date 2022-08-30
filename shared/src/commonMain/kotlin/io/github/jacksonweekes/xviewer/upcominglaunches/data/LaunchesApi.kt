package io.github.jacksonweekes.xviewer.upcominglaunches.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

class LaunchesApi(engine: HttpClientEngine) {

    private val client = HttpClient(engine) {
        expectSuccess = true
        install(ContentNegotiation) {
            json()
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
                host = "https://api.spacexdata.com/v4/"
                encodedPath = "launches/upcoming"
            }
        }.body()
}