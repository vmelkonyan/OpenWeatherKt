package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentWeather {
    @SerializedName("dt")
    @Expose
    private val dt: String? = null

    @SerializedName("sunrise")
    @Expose
    private val sunrise: String? = null

    @SerializedName("sunset")
    @Expose
    private val sunset: String? = null

    @SerializedName("temp")
    @Expose
    private val temp: String? = null

    @SerializedName("feels_like")
    @Expose
    private val feelsLike: String? = null

    @SerializedName("pressure")
    @Expose
    private val pressure: String? = null

    @SerializedName("humidity")
    @Expose
    private val humidity: String? = null

    @SerializedName("dew_point")
    @Expose
    private val dewPoint: String? = null

    @SerializedName("uvi")
    @Expose
    private val uvi: String? = null

    @SerializedName("clouds")
    @Expose
    private val clouds: String? = null

    @SerializedName("wind_speed")
    @Expose
    private val windSpeed: String? = null

    @SerializedName("wind_deg")
    @Expose
    private val windDeg: String? = null

    @SerializedName("weather")
    @Expose
    private val weather: List<Weather>? = null
}