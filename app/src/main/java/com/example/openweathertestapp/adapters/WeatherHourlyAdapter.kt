package com.example.openweathertestapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.openweathertestapp.R
import com.example.openweathertestapp.adapters.WeatherHourlyAdapter.WeatherViewHolder
import com.example.openweathertestapp.databinding.WeatherHourlyItemBinding
import com.example.openweathertestapp.models.WeatherDTO
import java.util.*

class WeatherHourlyAdapter(context: Context) : RecyclerView.Adapter<WeatherViewHolder>() {
    private var mViewHourlyWeather: List<WeatherDTO> = ArrayList()
    private val mInflater: LayoutInflater
    private val mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val weatherHourlyItemBindingtem: WeatherHourlyItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.weather_hourly_item, parent, false)
        return WeatherViewHolder(weatherHourlyItemBindingtem)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherDTO = mViewHourlyWeather[position]
        holder.weatherHourlyItemBinding.weather = weatherDTO
    }

    override fun getItemCount(): Int {
        return mViewHourlyWeather.size
    }

    inner class WeatherViewHolder(val weatherHourlyItemBinding: WeatherHourlyItemBinding) : ViewHolder(weatherHourlyItemBinding.root)

    fun setmViewHourlyWeather(mViewHourlyWeather: List<WeatherDTO>) {
        this.mViewHourlyWeather = mViewHourlyWeather
        notifyDataSetChanged()
    }

    init {
        mInflater = LayoutInflater.from(context)
        mContext = context
    }
}