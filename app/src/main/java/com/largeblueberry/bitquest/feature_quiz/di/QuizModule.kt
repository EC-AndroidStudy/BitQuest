package com.largeblueberry.bitquest.feature_quiz.di

import android.content.Context
import androidx.room.Room
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerDao
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.repository.WrongAnswerRepositoryImpl
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
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
 * 퀴즈 기능과 오답노트 기능에 필요한 모든 의존성을 제공하는 단일 모듈.
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

    @Provides
    fun provideWrongAnswerDao(database: QuizDatabase): WrongAnswerDao {
        return database.wrongAnswerDao()
    }

    // Quiz Repository
    @Provides
    @Singleton
    fun provideQuizRepository(dao: QuizDao): QuizRepository {
        return QuizRepositoryImpl(dao)
    }

    // WrongAnswer Repository
    @Provides
    @Singleton
    fun provideWrongAnswerRepository(dao: WrongAnswerDao): WrongAnswerRepository {
        return WrongAnswerRepositoryImpl(dao)
    }
}