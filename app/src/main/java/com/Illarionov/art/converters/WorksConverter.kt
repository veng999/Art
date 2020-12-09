package com.Illarionov.art.converters

import androidx.room.TypeConverter
import com.company.myartist.model.Type
import com.company.myartist.model.Work
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class WorksConverter{

    private val gson = Gson()

    @TypeConverter
    fun listFromString(string: String): List<Work> {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(string, listType)
    }

    @TypeConverter
    fun stringFromList(list: List<Work>): String{
        return gson.toJson(list)
    }
}