package com.example.openweathertestapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    fun getClient(client: OkHttpClient): RetrofiteAPIInterface {
        val retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        return retrofit.create(RetrofiteAPIInterface::class.java)
    }

}