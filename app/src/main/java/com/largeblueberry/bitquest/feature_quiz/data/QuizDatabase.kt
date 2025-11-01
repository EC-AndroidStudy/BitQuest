package com.largeblueberry.bitquest.feature_quiz.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// QuizDatabase.kt
@Database(
    entities = [QuizEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao
}