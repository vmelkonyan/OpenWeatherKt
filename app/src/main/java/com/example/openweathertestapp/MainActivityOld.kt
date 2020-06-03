package com.example.openweathertestapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathertestapp.models.MainWeatherModel
import com.example.openweathertestapp.service.RetrofiteAPIInterface
import com.example.openweathertestapp.service.StorageServiceImpl
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivityOld : AppCompatActivity() {

    private var adapter: WeatherHourlyAdapter? = null
    private val storageService by inject<StorageServiceImpl>()
    private val apiService by inject<RetrofiteAPIInterface>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val recyclerView = findViewById<RecyclerView>(R.id.rvAnimals)
        val horizontalLayoutManager = LinearLayoutManager(this@MainActivityOld, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        if (isNetworkConnected) {
            Toast.makeText(this, "EVERYTHING IS OK", Toast.LENGTH_LONG).show()
            button.setOnClickListener { v: View? ->
                val call3 = apiService.getCurrentWeather("40.177200", "44.503490", "metric", "dayli", getString(R.string.API_KEY))
                call3.enqueue(object : Callback<MainWeatherModel?> {
                    override fun onResponse(call: Call<MainWeatherModel?>, response: Response<MainWeatherModel?>) {
                        val userList = response.body()
                        val weatherDTOList: MutableList<WeatherDTO> = ArrayList()
                        userList?.hourly?.let {
                            for (hourly in it) {
                                weatherDTOList.add(WeatherDTO(hourly.dt, hourly.temp, hourly.weather?.first()?.icon))
                            }
                        }
                        storageService.cleareAll()
                        storageService.insertDatabase(weatherDTOList)
                        adapter = WeatherHourlyAdapter(this@MainActivityOld, storageService.allWeather)
                        recyclerView.adapter = adapter
                    }

                    override fun onFailure(call: Call<MainWeatherModel?>, t: Throwable) {
                        call.cancel()
                    }
                })
            }
        } else {
            Toast.makeText(this, "NOT CONNECTION", Toast.LENGTH_LONG).show()
            if (storageService.allWeather.isNotEmpty()) {
                adapter = WeatherHourlyAdapter(this@MainActivityOld, storageService.allWeather)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this, "STORAGE  IS EMPTY", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val isNetworkConnected: Boolean
        get() {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }

}