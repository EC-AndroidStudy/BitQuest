package com.largeblueberry.bitquest.feature_quiz.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz")
    suspend fun getAllQuizzes(): List<QuizEntity>

    @Query("SELECT * FROM quiz WHERE id = :id")
    suspend fun getQuizById(id: Int): QuizEntity?

    @Query("SELECT * FROM quiz WHERE category = :category")
    suspend fun getQuizzesByCategory(category: String): List<QuizEntity>

    // 누락되었던 함수 추가
    @Query("SELECT * FROM quiz WHERE id IN (:ids)")
    suspend fun getQuizzesByIds(ids: List<Int>): List<QuizEntity>

    @Query("SELECT * FROM quiz WHERE type = :type")
    suspend fun getQuizzesByType(type: QuizTypeEntity): List<QuizEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizzes(quizzes: List<QuizEntity>)

    @Delete
    suspend fun deleteQuiz(quiz: QuizEntity)

    @Query("SELECT COUNT(*) FROM quiz")
    suspend fun getQuizCount(): Int
}