package io.github.jacksonweekes.xviewer

import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

actual val platformModule = module {
    single {
        Darwin.create()
    }
}