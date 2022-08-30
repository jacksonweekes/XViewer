package io.github.jacksonweekes.xviewer.android

import android.app.Application
import io.github.jacksonweekes.xviewer.initKoin

class XViewer: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}