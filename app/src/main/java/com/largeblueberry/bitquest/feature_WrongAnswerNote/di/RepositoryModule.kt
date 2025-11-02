package com.largeblueberry.bitquest.feature_WrongAnswerNote.di

import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.repository.WrongAnswerRepositoryImpl
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindWrongAnswerRepository(
        wrongAnswerRepositoryImpl: WrongAnswerRepositoryImpl
    ): WrongAnswerRepository
}