package com.example.openweathertestapp.models

import android.text.format.DateFormat
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.openweathertestapp.R
import java.text.SimpleDateFormat
import java.util.*

class WeatherDTO(var date: String, var temp: String, var avatar: String) {
    var id: Int? = null

    fun getDateFromUTCTimestamp(mTimestamp: String?): String? {
        var date: String? = null
        val dataFormat = "dd-MM-yyyy - hh:mm a"
        try {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            cal.timeInMillis = java.lang.Long.valueOf(mTimestamp!!) * 1000L
            date = DateFormat.format(dataFormat, cal.timeInMillis).toString()
            val formatter = SimpleDateFormat(dataFormat)
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(date)
            val dateFormatter = SimpleDateFormat(dataFormat)
            dateFormatter.timeZone = TimeZone.getDefault()
            date = dateFormatter.format(value)
            return date
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

    companion object {
        @BindingAdapter("avatar")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            Glide.with(imageView.context)
                    .setDefaultRequestOptions(RequestOptions()
                            .circleCrop())
                    .load(imageURL)
                    .placeholder(R.drawable.loading)
                    .into(imageView)
        }
    }

}