package com.example.openweathertestapp.callback

import com.example.openweathertestapp.models.WeatherDTO

interface WeatherLoadCallBack {
    fun onSuccess(weatherDTOList: List<WeatherDTO?>?)
    fun onFailure(message: String?)
}