package com.largeblueberry.bitquest.feature_quiz.di

import android.content.Context
import androidx.room.Room
import com.largeblueberry.bitquest.feature_quiz.data.QuizDao
import com.largeblueberry.bitquest.feature_quiz.data.QuizDatabase
import com.largeblueberry.bitquest.feature_quiz.data.repository.QuizRepositoryImpl
import com.largeblueberry.bitquest.feature_quiz.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 퀴즈 기능에 필요한 모든 의존성을 제공하는 단일 모듈.
 */
@Module
@InstallIn(SingletonComponent::class)
object QuizModule {

    @Provides
    @Singleton
    fun provideQuizDatabase(@ApplicationContext context: Context): QuizDatabase {
        return Room.databaseBuilder(
            context,
            QuizDatabase::class.java,
            "quiz_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideQuizDao(database: QuizDatabase): QuizDao {
        return database.quizDao()
    }

    // MissingBinding 오류를 해결하는 핵심적인 부분
    @Provides
    @Singleton
    fun provideQuizRepository(dao: QuizDao): QuizRepository {
        return QuizRepositoryImpl(dao)
    }
}