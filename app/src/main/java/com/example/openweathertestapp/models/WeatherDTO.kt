package com.example.openweathertestapp.models

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WeatherDTO(var date: String, var temp: String, var avatar: String) {
    var id: Int? = null

    fun getDateFromUTCTimestamp(mTimestamp: String?): String? {
        var date: String? = null
        val dataFormat = "dd-MM-yyyy - hh:mm a"
        try {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            cal.timeInMillis = mTimestamp?.toLong()?.times(1000L) ?: System.currentTimeMillis()
            date = DateFormat.format(dataFormat, cal.timeInMillis).toString()
            val formatter = SimpleDateFormat(dataFormat, Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val value = formatter.parse(date)
            val dateFormatter = SimpleDateFormat(dataFormat, Locale.getDefault())
            dateFormatter.timeZone = TimeZone.getDefault()
            date = dateFormatter.format(value)
            return date
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

}