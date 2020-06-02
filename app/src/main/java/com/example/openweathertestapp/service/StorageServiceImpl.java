package com.example.openweathertestapp.service;

import android.content.Context;

import com.example.openweathertestapp.dao.SQLiteDatabaseHandler;
import com.example.openweathertestapp.models.WeatherDTO;

import java.util.List;

public class StorageServiceImpl implements StorageService {
    private SQLiteDatabaseHandler db;

    public StorageServiceImpl(Context context) {
        db = new SQLiteDatabaseHandler(context);
    }

    @Override
    public void insertDatabase(List<WeatherDTO> weatherDTOList) {
        for (WeatherDTO weatherDTO : weatherDTOList) {
            db.addWeatherDTO(weatherDTO);
        }
    }

    @Override
    public List<WeatherDTO> getAllWeather() {
        return db.getAllWeather();
    }

    @Override
    public void cleareAll() {
        db.cleareDb();
    }
}
