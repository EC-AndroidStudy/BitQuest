package com.largeblueberry.bitquest.feature_quiz.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerDao
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerEntity

@Database(
    entities = [
        QuizEntity::class,
        WrongAnswerEntity::class],
    version = 3, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao
    abstract fun wrongAnswerDao(): WrongAnswerDao
}