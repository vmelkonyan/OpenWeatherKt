package com.example.openweathertestapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import lombok.Getter
import lombok.Setter

@Setter
@Getter
class Weather {
    @SerializedName("id")
    @Expose
    private val id: String? = null

    @SerializedName("main")
    @Expose
    private val main: String? = null

    @SerializedName("description")
    @Expose
    private val description: String? = null

    @SerializedName("icon")
    @Expose
    private val icon: String? = null
}