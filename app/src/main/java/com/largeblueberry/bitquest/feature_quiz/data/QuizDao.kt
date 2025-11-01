package com.largeblueberry.bitquest.feature_quiz.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz")
    suspend fun getAllQuizzes(): List<Quiz>

    @Query("SELECT * FROM quiz WHERE id = :id")
    suspend fun getQuizById(id: Int): Quiz?

    @Query("SELECT * FROM quiz WHERE category = :category")
    suspend fun getQuizzesByCategory(category: String): List<Quiz>

    @Query("SELECT * FROM quiz WHERE type = :type")
    suspend fun getQuizzesByType(type: QuizTypeEntity): List<Quiz>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: Quiz)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizzes(quizzes: List<Quiz>)

    @Delete
    suspend fun deleteQuiz(quiz: Quiz)
}