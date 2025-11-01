package com.largeblueberry.bitquest.feature_quiz.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun fromQuizType(value: QuizTypeEntity): String {
        return value.name
    }

    @TypeConverter
    fun toQuizType(value: String): QuizTypeEntity {
        return QuizTypeEntity.valueOf(value)
    }
}