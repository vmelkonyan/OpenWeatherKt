package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeather(
        @SerializedName("dt")
        @Expose
        val dt: String? = null,

        @SerializedName("sunrise")
        @Expose
        val sunrise: String? = null,

        @SerializedName("sunset")
        @Expose
        val sunset: String? = null,

        @SerializedName("temp")
        @Expose
        val temp: String? = null,

        @SerializedName("feels_like")
        @Expose
        val feelsLike: String? = null,

        @SerializedName("pressure")
        @Expose
        val pressure: String? = null,

        @SerializedName("humidity")
        @Expose
        val humidity: String? = null,

        @SerializedName("dew_point")
        @Expose
        val dewPoint: String? = null,

        @SerializedName("uvi")
        @Expose
        val uvi: String? = null,

        @SerializedName("clouds")
        @Expose
        val clouds: String? = null,

        @SerializedName("wind_speed")
        @Expose
        val windSpeed: String? = null,

        @SerializedName("wind_deg")
        @Expose
        val windDeg: String? = null,

        @SerializedName("weather")
        @Expose
        val weather: List<Weather>? = null
)