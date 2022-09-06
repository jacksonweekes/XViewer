package io.github.jacksonweekes.xviewer.android

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import io.github.jacksonweekes.xviewer.initKoin
import org.koin.dsl.module

class XViewer: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            module {
                single<SharedPreferences> {
                    this@XViewer.getSharedPreferences("XViewerPreferences", Context.MODE_PRIVATE)
                }
            }
        )
    }
}