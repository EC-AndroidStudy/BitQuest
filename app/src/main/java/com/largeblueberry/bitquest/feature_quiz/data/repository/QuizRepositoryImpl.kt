package com.largeblueberry.bitquest.feature_quiz.data.repository

import com.largeblueberry.bitquest.feature_quiz.data.QuizDao
import com.largeblueberry.bitquest.feature_quiz.data.mapper.toQuiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao
) : QuizRepository {
    override suspend fun getQuizzesByCategory(category: String): List<Quiz> {
        return dao.getQuizzesByCategory(category).map { it.toQuiz() }
    }

    override suspend fun getQuizzesByIds(ids: List<Int>): List<Quiz> {
        return dao.getQuizzesByIds(ids).map { it.toQuiz() }
    }
}