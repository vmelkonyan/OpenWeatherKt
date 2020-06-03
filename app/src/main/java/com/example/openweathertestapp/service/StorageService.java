package com.example.openweathertestapp.service;


import com.example.openweathertestapp.models.WeatherDTO;

import java.util.List;

public interface StorageService {

    void insertDatabase(List<WeatherDTO> weatherDTOList);

    List<WeatherDTO> getAllWeather();

    void clearAll();
}
