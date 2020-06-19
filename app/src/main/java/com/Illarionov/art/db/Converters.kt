package com.Illarionov.art.db

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun listOfStringToString(list: List<String>?): String {
        return list?.joinToString(":") ?: ""
    }

    @TypeConverter
    fun stringToListOfString(line: String?): List<String> {
        return line?.split(":") ?: emptyList()
    }
}
