package com.example.openweathertestapp.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.openweathertestapp.models.WeatherDTO
import java.util.*

class SQLiteDatabaseHandler(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createWeatherTable = ("CREATE TABLE WEATHER_TABLE" + "( "
                + WEATHER_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WEATHER_TABLE_DATE + " TEXT, "
                + WEATHER_TABLE_TEMP + " TEXT, "
                + WEATHER_TABLE_ICON + " TEXT )")
        db.execSQL(createWeatherTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    val allWeather: List<WeatherDTO>
        get() {
            val weatherDTOList: MutableList<WeatherDTO> = LinkedList()
            val query = "SELECT  * FROM $WEATHER_TABLE"
            val db = this.writableDatabase
            val cursor = db.rawQuery(query, null)
            var weatherDTO: WeatherDTO? = null
            if (cursor.moveToFirst()) {
                do {
                    weatherDTO = WeatherDTO(cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3))
                    weatherDTO.id = cursor.getString(0).toInt()
                    weatherDTOList.add(weatherDTO)
                } while (cursor.moveToNext())
            }
            return weatherDTOList
        }

    fun cleareDb() {
        val db = this.writableDatabase
        db.delete(WEATHER_TABLE, null, null)
    }

    fun addWeatherDTO(weatherDTO: WeatherDTO) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(WEATHER_TABLE_DATE, weatherDTO.date)
        values.put(WEATHER_TABLE_TEMP, weatherDTO.temp)
        values.put(WEATHER_TABLE_ICON, weatherDTO.icon)
        // insert
        db.insert(WEATHER_TABLE, null, values)
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "WEATHER_DB"
        private const val WEATHER_TABLE = "WEATHER_TABLE"
        private const val WEATHER_TABLE_ID = "WEATHER_TABLE_ID"
        private const val WEATHER_TABLE_DATE = "WEATHER_TABLE_DATE"
        private const val WEATHER_TABLE_TEMP = "WEATHER_TABLE_TEMP"
        private const val WEATHER_TABLE_ICON = "WEATHER_TABLE_ICON"
    }
}