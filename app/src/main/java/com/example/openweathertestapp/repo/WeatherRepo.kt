package com.example.openweathertestapp.repo

import com.example.openweathertestapp.callback.WeatherLoadCallBack
import com.example.openweathertestapp.models.MainWeatherModel
import com.example.openweathertestapp.models.WeatherDTO
import com.example.openweathertestapp.service.RetrofiteAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class WeatherRepo(private val apiService: RetrofiteAPIInterface) {

    fun getWeatherList(apiKey: String?, weatherLoadCallBack: WeatherLoadCallBack) {
        val weatherDTOList: MutableList<WeatherDTO> = ArrayList()
        val call = apiService.getCurrentWeather(
                "40.177200", "44.503490", "metric", "dayli", apiKey)
        call.enqueue(object : Callback<MainWeatherModel?> {
            override fun onResponse(call: Call<MainWeatherModel?>, response: Response<MainWeatherModel?>) {
                val userList = response.body()
                userList?.hourly?.let {
                    for (hourly in it) {
                        val url = "http://openweathermap.org/img/wn/" + hourly.weather?.get(0)?.icon + "@2x.png"
                        weatherDTOList.add(WeatherDTO(hourly.dt, hourly.temp, url))
                    }
                }
                weatherLoadCallBack.onSuccess(weatherDTOList)
            }

            override fun onFailure(call: Call<MainWeatherModel?>, t: Throwable) {
                call.cancel()
                weatherLoadCallBack.onFailure(t.message)
            }
        })
    }
}