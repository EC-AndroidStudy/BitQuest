package com.largeblueberry.bitquest.feature_quiz.data.repository

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

    suspend fun initializeQuizData() {
        if (quizDao.getQuizCount() == 0) {
            val quizEntities = jsonLoader.loadQuizzesFromAssets()
            quizDao.insertQuizzes(quizEntities)
        }
    }

    override suspend fun getAllQuizzes(): List<Quiz> {
        val entities = quizDao.getAllQuizzes()
        return mapper.mapToDomainList(entities)
    }

    override suspend fun getQuizById(id: Int): Quiz? {
        val entity = quizDao.getQuizById(id)
        return mapper.mapToDomain(entity)
    }

    override suspend fun getQuizzesByCategory(category: String): List<Quiz> {
        val entities = quizDao.getQuizzesByCategory(category)
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
