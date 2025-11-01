package com.largeblueberry.bitquest.feature_WrongAnswerNote.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WrongAnswerEntity::class], version = 2, exportSchema = false) // 버전을 1에서 2로 올립니다.
abstract class WrongAnswerDatabase : RoomDatabase() {
    abstract fun wrongAnswerDao(): WrongAnswerDao
}