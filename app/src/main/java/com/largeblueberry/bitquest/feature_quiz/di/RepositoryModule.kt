package com.largeblueberry.bitquest.feature_quiz.di

import com.largeblueberry.bitquest.feature_quiz.data.repository.QuizRepositoryImpl
import com.largeblueberry.bitquest.feature_quiz.domain.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ): QuizRepository
}