package io.github.jacksonweekes.xviewer

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

fun initKoinIos(userDefaults: NSUserDefaults) = initKoin(
    module {
        single<Settings> { AppleSettings(userDefaults) }
    }
)

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