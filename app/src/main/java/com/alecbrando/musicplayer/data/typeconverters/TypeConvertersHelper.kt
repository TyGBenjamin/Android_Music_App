package com.alecbrando.musicplayer.data.typeconverters

import androidx.room.TypeConverter
import com.alecbrando.musicplayer.domains.models.Songs

class TypeConvertersHelper {
    private val separator = ","
    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }
    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }
}