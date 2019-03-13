package com.rdenq.bucketlist.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*

class CustomConverters {

    @TypeConverter
    fun listToString(list: List<String>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToList(value: String?): List<String> {
        val objects = Gson().fromJson(value, Array<String>::class.java) as Array<String>
        val list = objects.toList()
        return list
    }

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

}
