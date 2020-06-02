package com.example.openweathertestapp

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathertestapp.adapters.WeatherHourlyAdapter
import com.example.openweathertestapp.models.MainWeatherModel
import com.example.openweathertestapp.models.WeatherDTO
import com.example.openweathertestapp.service.RetrofitClientInstance
import com.example.openweathertestapp.service.RetrofiteAPIInterface
import com.example.openweathertestapp.service.StorageServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private var retrofiteAPIInterface: RetrofiteAPIInterface? = null
    private var adapter: WeatherHourlyAdapter? = null
    private val storageService: StorageServiceImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val recyclerView = findViewById<RecyclerView>(R.id.rvAnimals)
        val horizontalLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        if (isNetworkConnected) {
            Toast.makeText(this, "EVERYTHING IS OK", Toast.LENGTH_LONG).show()
            retrofiteAPIInterface = RetrofitClientInstance.getClient().create(RetrofiteAPIInterface::class.java)
            button.setOnClickListener { v: View? ->
                val call3 = retrofiteAPIInterface.getCurrentWeather("40.177200", "44.503490", "metric", "dayli", getString(R.string.API_KEY))
                call3.enqueue(object : Callback<MainWeatherModel?> {
                    override fun onResponse(call: Call<MainWeatherModel?>, response: Response<MainWeatherModel?>) {
                        val userList = response.body()
                        val weatherDTOList: MutableList<WeatherDTO> = ArrayList()
                        for (hourly in userList.getHourly()) {
                            weatherDTOList.add(WeatherDTO(hourly.getDt(), hourly.getTemp(), hourly.getWeather().get(0).getIcon()))
                        }
                        storageService.cleareAll()
                        storageService.insertDatabase(weatherDTOList)
                        adapter = WeatherHourlyAdapter(this@MainActivity, storageService.allWeather)
                        recyclerView.adapter = adapter
                    }

                    override fun onFailure(call: Call<MainWeatherModel?>, t: Throwable) {
                        call.cancel()
                    }
                })
            }
        } else {
            Toast.makeText(this, "NOT CONNECTION", Toast.LENGTH_LONG).show()
            if (storageService.allWeather.size > 0) {
                adapter = WeatherHourlyAdapter(this@MainActivity, storageService.allWeather)
                recyclerView.adapter = adapter
            } else {
                Toast.makeText(this, "STORAGE  IS EMPTY", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val isNetworkConnected: Boolean
        private get() {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        }

    companion object {
        private const val REQUEST_LOCATION = 1
    }

    init {
        storageService = StorageServiceImpl(this)
    }
}