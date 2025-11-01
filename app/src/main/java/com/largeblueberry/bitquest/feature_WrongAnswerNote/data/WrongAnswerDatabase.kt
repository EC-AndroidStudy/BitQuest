package com.largeblueberry.bitquest.feature_WrongAnswerNote.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WrongAnswerEntity::class], version = 1, exportSchema = false)
abstract class WrongAnswerDatabase : RoomDatabase() {
    abstract fun wrongAnswerDao(): WrongAnswerDao
}