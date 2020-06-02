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