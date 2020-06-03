package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainWeatherModel {
    @SerializedName("lat")
    @Expose
    private val lat: String? = null

    @SerializedName("lon")
    @Expose
    private val lon: String? = null

    @SerializedName("timezone")
    @Expose
    private val timezone: String? = null

    @SerializedName("timezone_offset")
    @Expose
    private val timezoneOffset: String? = null

    @SerializedName("current")
    @Expose
    private val current: CurrentWeather? = null

    @SerializedName("hourly")
    @Expose
    val hourly: List<Hourly>? = null

    @SerializedName("daily")
    @Expose
    private val daily: List<Daily>? = null
}