package com.largeblueberry.bitquest.feature_quiz.domain

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType

interface QuizRepository {
    suspend fun getAllQuizzes(): List<Quiz>
    suspend fun getQuizById(id: Int): Quiz?
    suspend fun getQuizzesByCategory(category: String): List<Quiz>
    suspend fun getQuizzesByType(type: QuizType): List<Quiz>
    suspend fun insertQuiz(quiz: Quiz)
    suspend fun insertQuizzes(quizzes: List<Quiz>)
    suspend fun deleteQuiz(quiz: Quiz)
}