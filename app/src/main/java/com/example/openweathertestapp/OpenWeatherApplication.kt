package com.example.openweathertestapp

import android.app.Application
import com.example.openweathertestapp.di.apiModule
import com.example.openweathertestapp.di.appModule
import com.example.openweathertestapp.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OpenWeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@OpenWeatherApplication)
            modules(appModule, apiModule, repoModule)
        }
    }
}
