package com.largeblueberry.bitquest.feature_quiz.domain.repository

import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz

interface QuizRepository {
    suspend fun getQuizzesByCategory(category: String): List<Quiz>
    suspend fun getQuizzesByIds(ids: List<Int>): List<Quiz>
}