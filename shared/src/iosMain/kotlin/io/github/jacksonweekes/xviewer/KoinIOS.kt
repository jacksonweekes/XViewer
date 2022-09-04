package io.github.jacksonweekes.xviewer

import io.github.jacksonweekes.xviewer.upcominglaunches.presentation.UpcomingLaunchesViewModel
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

fun initKoinIos() = initKoin()

actual val platformModule = module {
    single {
        Darwin.create()
    }
    single {
        UpcomingLaunchesCallbackViewModel(get(), get())
    }
}

@Suppress("Unused") // Called from Swift
object KotlinDependencies: KoinComponent {
    fun getUpcomingLaunchesViewModel() =
        getKoin().get<UpcomingLaunchesCallbackViewModel>()
}