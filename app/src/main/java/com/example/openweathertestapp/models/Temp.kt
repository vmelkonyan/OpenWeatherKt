package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Temp {
    @SerializedName("day")
    @Expose
    private val day: String? = null

    @SerializedName("min")
    @Expose
    private val min: String? = null

    @SerializedName("max")
    @Expose
    private val max: String? = null

    @SerializedName("night")
    @Expose
    private val night: String? = null

    @SerializedName("eve")
    @Expose
    private val eve: String? = null

    @SerializedName("morn")
    @Expose
    private val morn: String? = null
}