package com.example.openweathertestapp.adapters

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openweathertestapp.R
import com.example.openweathertestapp.models.WeatherDTO
import java.text.SimpleDateFormat
import java.util.*

class WeatherHourlyAdapter(context: Context, mViewHourlyWeather: List<WeatherDTO>) : RecyclerView.Adapter<WeatherHourlyAdapter.ViewHolder>() {
    private val mViewHourlyWeather: List<WeatherDTO>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private val mContext: Context

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.weather_hourly_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the view and textview in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weatherDTO = mViewHourlyWeather[position]
        val url = "http://openweathermap.org/img/wn/" + weatherDTO.icon + "@2x.png"
        Log.i("ICON ------>", "ICON ======== $url")
        Glide.with(mContext)
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .into(holder.weatherIcon)
        holder.weatherDegre.text = weatherDTO.temp
        holder.weatherDay.text = getDateFromUTCTimestamp(java.lang.Long.valueOf(weatherDTO.date), "dd-MM-yyyy - hh:mm a")
    }

    fun getDateFromUTCTimestamp(mTimestamp: Long, mDateFormate: String?): String? {
        var date: String? = null
        try {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            cal.timeInMillis = mTimestamp * 1000L
            date = DateFormat.format(mDateFormate, cal.timeInMillis).toString()
            val formatter = SimpleDateFormat(mDateFormate)
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(date)
            val dateFormatter = SimpleDateFormat(mDateFormate)
            dateFormatter.timeZone = TimeZone.getDefault()
            date = dateFormatter.format(value)
            return date
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mViewHourlyWeather.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var weatherIcon: ImageView
        var weatherDegre: TextView
        var weatherDay: TextView
        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            weatherIcon = itemView.findViewById(R.id.weatherIcon)
            weatherDegre = itemView.findViewById(R.id.weatherDegre)
            weatherDay = itemView.findViewById(R.id.weatherDay)
            itemView.setOnClickListener(this)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): WeatherDTO {
        return mViewHourlyWeather[id]
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        this.mViewHourlyWeather = mViewHourlyWeather
        mContext = context
    }
}