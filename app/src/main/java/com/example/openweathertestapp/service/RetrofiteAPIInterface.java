package com.example.openweathertestapp.service;

import com.example.openweathertestapp.models.MainWeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofiteAPIInterface {
    @GET("onecall?")
    Call<MainWeatherModel> getCurrentWeather(@Query("lat") String lat,
                                             @Query("lon") String lon,
                                             @Query("units") String units,
                                             @Query("exclude") String exclude,
                                             @Query("appid") String appid
    );
}
