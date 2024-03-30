package com.example.room_db_component

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromDateToLong(value: Date) : Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long) : Date {
        return Date(value)
    }

}