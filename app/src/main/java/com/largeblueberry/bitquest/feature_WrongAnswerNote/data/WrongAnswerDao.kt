package com.largeblueberry.bitquest.feature_WrongAnswerNote.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WrongAnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<WrongAnswerEntity>)

    @Query("SELECT * FROM wrong_answers ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<WrongAnswerEntity>>

    @Query("DELETE FROM wrong_answers")
    suspend fun clear()
}