package com.largeblueberry.bitquest.feature_quiz.data.repository

import android.util.Log // Log import 추가
import com.largeblueberry.bitquest.feature_quiz.data.QuizDao
import com.largeblueberry.bitquest.feature_quiz.data.QuizTypeEntity
import com.largeblueberry.bitquest.feature_quiz.data.mapper.QuizMapper
import com.largeblueberry.bitquest.feature_quiz.data.util.QuizJsonLoader
import com.largeblueberry.bitquest.feature_quiz.domain.QuizRepository
import javax.inject.Inject
import javax.inject.Singleton
import com.largeblueberry.bitquest.feature_quiz.domain.model.Quiz
import com.largeblueberry.bitquest.feature_quiz.domain.model.QuizType

@Singleton
class QuizRepositoryImpl @Inject constructor(
    private val quizDao: QuizDao,
    private val mapper: QuizMapper,
    private val jsonLoader: QuizJsonLoader
) : QuizRepository {

    private val TAG = "QuizRepo" // 로그 태그 정의

    suspend fun initializeQuizData() {
        val currentCount = quizDao.getQuizCount()
        Log.d(TAG, "1. Current DB count: $currentCount") // 👈 1. 현재 DB 상태 확인

        if (currentCount == 0) {
            val quizEntities = jsonLoader.loadQuizzesFromAssets()

            Log.d(TAG, "2. Loaded entities from JSON: ${quizEntities.size}") // 👈 2. JSON 로드 성공 여부 확인

            if (quizEntities.isNotEmpty()) {
                quizDao.insertQuizzes(quizEntities)
                Log.d(TAG, "3. Insertion complete. New DB count: ${quizDao.getQuizCount()}") // 👈 3. 삽입 후 DB 상태 확인
            } else {
                Log.e(TAG, "JSON file loaded, but entities list is empty!")
            }
        }
    }

    override suspend fun getAllQuizzes(): List<Quiz> {
        val entities = quizDao.getAllQuizzes()
        Log.d(TAG, "Query: getAllQuizzes returned ${entities.size} entities.") // 👈 쿼리 결과 로그 추가
        return mapper.mapToDomainList(entities)
    }

    override suspend fun getQuizById(id: Int): Quiz? {
        val entity = quizDao.getQuizById(id)
        return mapper.mapToDomain(entity)
    }

    override suspend fun getQuizzesByCategory(category: String): List<Quiz> {
        val entities = quizDao.getQuizzesByCategory(category)
        Log.d(TAG, "Query: getQuizzesByCategory('$category') returned ${entities.size} entities.") // 👈 쿼리 결과 로그 추가
        return mapper.mapToDomainList(entities)
    }

    override suspend fun getQuizzesByType(type: QuizType): List<Quiz> {
        val entityType = when (type) {
            QuizType.OX -> QuizTypeEntity.OX
            QuizType.MULTIPLE_CHOICE -> QuizTypeEntity.MULTIPLE_CHOICE
        }
        val entities = quizDao.getQuizzesByType(entityType)
        return mapper.mapToDomainList(entities)
    }

    override suspend fun insertQuiz(quiz: Quiz) {
        val entity = mapper.mapToEntity(quiz)
        quizDao.insertQuiz(entity)
    }

    override suspend fun insertQuizzes(quizzes: List<Quiz>) {
        val entities = mapper.mapToEntityList(quizzes)
        quizDao.insertQuizzes(entities)
    }

    override suspend fun deleteQuiz(quiz: Quiz) {
        val entity = mapper.mapToEntity(quiz)
        quizDao.deleteQuiz(entity)
    }
}
