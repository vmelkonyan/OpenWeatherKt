package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import lombok.Getter
import lombok.Setter

@Setter
@Getter
class FeelsLike {
    @SerializedName("day")
    @Expose
    private val day: String? = null

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