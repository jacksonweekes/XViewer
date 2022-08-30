package io.github.jacksonweekes.xviewer.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.jacksonweekes.xviewer.Greeting
import android.widget.TextView
import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import io.github.jacksonweekes.xviewer.upcominglaunches.data.LaunchesApi
import io.github.jacksonweekes.xviewer.upcominglaunches.data.LaunchesRepository
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewModel
import io.ktor.client.engine.okhttp.*

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    val viewModel = UpcomingLaunchesViewModel(
        LaunchesRepository(
            LaunchesApi(OkHttp.create(),
                Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "XViewer"))
        )
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
