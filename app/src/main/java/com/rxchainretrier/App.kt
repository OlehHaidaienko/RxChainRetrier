package com.rxchainretrier

import android.app.Application
import com.rxchainretrier.provider.AppProvider
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppProvider.appInstance = this
    }
}