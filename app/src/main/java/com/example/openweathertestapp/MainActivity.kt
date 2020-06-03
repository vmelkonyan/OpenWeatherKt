package com.example.openweathertestapp

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openweathertestapp.adapters.WeatherHourlyAdapter
import com.example.openweathertestapp.callback.WeatherLoadCallBack
import com.example.openweathertestapp.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.openweathertestapp.models.WeatherDTO
import com.example.openweathertestapp.service.StorageServiceImpl
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val storageService by inject<StorageServiceImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val weatherHourlyAdapter = WeatherHourlyAdapter()
        // bind RecyclerView
        activityMainBinding.viewWeather.apply {
            val horizontalLayoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.HORIZONTAL, false)
           layoutManager = horizontalLayoutManager
           setHasFixedSize(true)
           this.adapter = weatherHourlyAdapter
        }

        val vm by viewModel<MainViewModel>()

        if (isNetworkConnected) {
            vm.getWeatherList(getString(R.string.API_KEY), object : WeatherLoadCallBack {
                override fun onSuccess(weatherDTOList: List<WeatherDTO>) {
                    storageService.clearAll()
                    storageService.insertDatabase(weatherDTOList)
                    weatherHourlyAdapter.setViewHourlyWeather(weatherDTOList)
                }

                override fun onFailure(message: String?) {}
            })
        } else {
            if (storageService.allWeather.isEmpty()) {
                Toast.makeText(this, R.string.not_connection_load, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.not_connection_update, Toast.LENGTH_LONG).show()
                weatherHourlyAdapter.setViewHourlyWeather(storageService.allWeather)
            }
        }
    }

    private val isNetworkConnected: Boolean
        get() {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
}