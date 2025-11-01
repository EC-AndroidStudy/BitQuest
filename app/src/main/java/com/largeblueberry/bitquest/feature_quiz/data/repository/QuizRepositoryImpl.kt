package com.largeblueberry.bitquest.feature_quiz.data.repository

import com.largeblueberry.bitquest.feature_quiz.data.QuizDao
import com.largeblueberry.bitquest.feature_quiz.data.mapper.toQuiz
import com.largeblueberry.bitquest.feature_quiz.data.mapper.toQuizEntity
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType
import com.largeblueberry.bitquest.feature_quiz.domain.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao
) : QuizRepository {

    override suspend fun getAllQuizzes(): List<Quiz> = withContext(Dispatchers.IO) {
        try {
            dao.getAllQuizzes().map { it.toQuiz() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getQuizById(id: Int): Quiz? = withContext(Dispatchers.IO) {
        try {
            dao.getQuizById(id)?.toQuiz()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getQuizzesByCategory(category: String): List<Quiz> = withContext(Dispatchers.IO) {
        try {
            if (category.isBlank()) return@withContext emptyList()

            val quizEntities = dao.getQuizzesByCategory(category.trim())
            quizEntities.map { it.toQuiz() }
        } catch (e: Exception) {
            // 로그 추가 권장: Log.e("QuizRepository", "Error getting quizzes by category: $category", e)
            emptyList()
        }
    }

    override suspend fun getQuizzesByType(type: QuizType): List<Quiz> = withContext(Dispatchers.IO) {
        try {
            dao.getQuizzesByType(type.name).map { it.toQuiz() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getQuizzesByIds(ids: List<Int>): List<Quiz> = withContext(Dispatchers.IO) {
        try {
            if (ids.isEmpty()) return@withContext emptyList()

            // 유효한 ID만 필터링
            val validIds = ids.filter { it > 0 }
            if (validIds.isEmpty()) return@withContext emptyList()

            val quizEntities = dao.getQuizzesByIds(validIds)
            quizEntities.map { it.toQuiz() }
        } catch (e: Exception) {
            // 로그 추가 권장: Log.e("QuizRepository", "Error getting quizzes by ids: $ids", e)
            emptyList()
        }
    }

    override suspend fun insertQuiz(quiz: Quiz) = withContext(Dispatchers.IO) {
        try {
            dao.insertQuiz(quiz.toQuizEntity())
        } catch (e: Exception) {
            // 로그 추가 권장: Log.e("QuizRepository", "Error inserting quiz", e)
            throw e
        }
    }

    override suspend fun insertQuizzes(quizzes: List<Quiz>) = withContext(Dispatchers.IO) {
        try {
            dao.insertQuizzes(quizzes.map { it.toQuizEntity() })
        } catch (e: Exception) {
            // 로그 추가 권장: Log.e("QuizRepository", "Error inserting quizzes", e)
            throw e
        }
    }

    override suspend fun deleteQuiz(quiz: Quiz) = withContext(Dispatchers.IO) {
        try {
            dao.deleteQuiz(quiz.toQuizEntity())
        } catch (e: Exception) {
            // 로그 추가 권장: Log.e("QuizRepository", "Error deleting quiz", e)
            throw e
        }
    }
}