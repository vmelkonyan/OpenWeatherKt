package com.example.openweathertestapp.models

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Setter
@Getter
@NoArgsConstructor
class WeatherDTO(var date: String?, var temp: String?, var icon: String?) {
    var id: Int? = null
}