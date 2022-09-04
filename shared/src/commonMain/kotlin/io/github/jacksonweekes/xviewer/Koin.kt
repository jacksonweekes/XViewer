package io.github.jacksonweekes.xviewer

import co.touchlab.kermit.Logger
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import io.github.jacksonweekes.xviewer.upcominglaunches.data.LaunchesApi
import io.github.jacksonweekes.xviewer.upcominglaunches.data.LaunchesRepository
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesStateReducer
import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(): KoinApplication {
    val koinApplication = startKoin {
        modules(
            coreModule,
            platformModule
        )
    }

    val koin = koinApplication.koin

    val kermit = koin.get<Logger>()
    kermit.v("Koin application initialised")

    return koinApplication
}

private val coreModule = module {
    single {
        LaunchesApi(get(), get())
    }

    single {
        LaunchesRepository(get())
    }

    single {
        Logger(config = StaticConfig(logWriterList = listOf(platformLogWriter())), "XViewer")
    }

    single {
        UpcomingLaunchesViewModel(get(), get(), get())
    }

    single {
        UpcomingLaunchesStateReducer()
    }
}

expect val platformModule: Module
