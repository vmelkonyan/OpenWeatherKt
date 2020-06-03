package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeelsLike(
        @SerializedName("day")
        @Expose
        val day: String? = null,

        @SerializedName("night")
        @Expose
        val night: String? = null,

        @SerializedName("eve")
        @Expose
        val eve: String? = null,

        @SerializedName("morn")
        @Expose
        val morn: String? = null
)