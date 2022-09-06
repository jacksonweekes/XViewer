package io.github.jacksonweekes.xviewer

import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import io.ktor.client.engine.okhttp.*
import org.koin.dsl.module

actual val platformModule = module {
    single {
        OkHttp.create()
    }

    single<Settings> {
        AndroidSettings(get())
    }
}