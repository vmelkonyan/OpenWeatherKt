package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("main")
    @Expose
    val main: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("icon")
    @Expose
    val icon: String? = null
}