package com.example.openweathertestapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.openweathertestapp.callback.WeatherLoadCallBack
import com.example.openweathertestapp.repo.WeatherRepo

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val weatherRepo: WeatherRepo
    fun getWeatherList(appKey: String?, weatherLoadCallBack: WeatherLoadCallBack?) {
        if (weatherLoadCallBack != null) {
            weatherRepo.getWeaterList(appKey, weatherLoadCallBack)
        }
    }

    init {
        weatherRepo = WeatherRepo()
    }
}