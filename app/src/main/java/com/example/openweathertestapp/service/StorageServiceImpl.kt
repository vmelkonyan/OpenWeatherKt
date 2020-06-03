package com.example.openweathertestapp.service

import com.example.openweathertestapp.dao.SQLiteDatabaseHandler
import com.example.openweathertestapp.models.WeatherDTO

class StorageServiceImpl(private val db: SQLiteDatabaseHandler) : StorageService {

    override fun insertDatabase(weatherDTOList: List<WeatherDTO>) {
        for (weatherDTO in weatherDTOList) {
            db.addWeatherDTO(weatherDTO)
        }
    }

    override fun getAllWeather(): List<WeatherDTO> {
        return db.allWeather
    }

    override fun clearAll() {
        db.cleareDb()
    }

}