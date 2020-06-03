package com.example.openweathertestapp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathertestapp.adapters.WeatherHourlyAdapter
import com.example.openweathertestapp.callback.WeatherLoadCallBack
import com.example.openweathertestapp.databinding.ActivityMainBinding
import com.example.openweathertestapp.models.WeatherDTO
import com.example.openweathertestapp.service.StorageServiceImpl
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private var adapter: WeatherHourlyAdapter? = null
    private val storageService by inject<StorageServiceImpl>()
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // bind RecyclerView
        val recyclerView: RecyclerView = activityMainBinding.viewWeather
        val horizontalLayoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManager
        recyclerView.setHasFixedSize(true)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = WeatherHourlyAdapter(this)
        recyclerView.adapter = adapter
        if (isNetworkConnected) {
            mainViewModel!!.getWeatherList(getString(R.string.API_KEY), object : WeatherLoadCallBack() {
                fun onSuccess(weatherDTOList: List<WeatherDTO?>?) {
                    storageService.cleareAll()
                    storageService.insertDatabase(weatherDTOList!!)
                    adapter!!.setmViewHourlyWeather(weatherDTOList as List<WeatherDTO>)
                }

                fun onFailure(message: String?) {}
            })
        } else {
            if (storageService.allWeather.isEmpty()) {
                Toast.makeText(this, R.string.not_connection_load, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.not_connection_update, Toast.LENGTH_LONG).show()
                adapter!!.setmViewHourlyWeather(storageService.allWeather)
            }
        }
    }

    private val isNetworkConnected: Boolean
        private get() {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
}