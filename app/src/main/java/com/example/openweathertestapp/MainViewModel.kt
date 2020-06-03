package com.example.openweathertestapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.openweathertestapp.callback.WeatherLoadCallBack
import com.example.openweathertestapp.repo.WeatherRepo

class MainViewModel(application: Application,
                    private val weatherRepo: WeatherRepo) : AndroidViewModel(application) {

    fun getWeatherList(appKey: String?, weatherLoadCallBack: WeatherLoadCallBack?) {
        if (weatherLoadCallBack != null) {
            weatherRepo.getWeatherList(appKey, weatherLoadCallBack)
        }
    }

}