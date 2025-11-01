package com.largeblueberry.bitquest.feature_WrongAnswerNote.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WrongAnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWrongAnswer(wrongAnswer: WrongAnswerEntity)

    @Query("DELETE FROM wrong_answers WHERE id = :id")
    suspend fun deleteWrongAnswerById(id: Int)

    @Query("SELECT * FROM wrong_answers ORDER BY createdAt DESC")
    fun getAllWrongAnswers(): Flow<List<WrongAnswerEntity>>

    @Query("SELECT * FROM wrong_answers WHERE category = :category ORDER BY createdAt DESC")
    fun getWrongAnswersByCategory(category: String): Flow<List<WrongAnswerEntity>>

    @Query("SELECT * FROM wrong_answers WHERE id = :id")
    suspend fun getWrongAnswerById(id: Int): WrongAnswerEntity?
}