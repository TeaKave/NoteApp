package com.teakave.infrastructure.feature.common.database.converter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun fromTimeStamp(value: Long) = Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date): Long? = date.time

}