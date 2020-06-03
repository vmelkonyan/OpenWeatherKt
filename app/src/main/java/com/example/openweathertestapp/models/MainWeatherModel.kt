package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainWeatherModel(

        @SerializedName("lat")
        @Expose
        val lat: String? = null,

        @SerializedName("lon")
        @Expose
        val lon: String? = null,

        @SerializedName("timezone")
        @Expose
        val timezone: String? = null,

        @SerializedName("timezone_offset")
        @Expose
        val timezoneOffset: String? = null,

        @SerializedName("current")
        @Expose
        val current: CurrentWeather? = null,

        @SerializedName("hourly")
        @Expose
        val hourly: List<Hourly>? = null,

        @SerializedName("daily")
        @Expose
        val daily: List<Daily>? = null

)