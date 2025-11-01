package com.largeblueberry.bitquest.feature_FieldSelection.di

import com.largeblueberry.bitquest.feature_FieldSelection.data.FieldRepositoryImpl
import com.largeblueberry.bitquest.feature_FieldSelection.domain.FieldRepository
import com.largeblueberry.bitquest.feature_FieldSelection.domain.GetFieldUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // 또는 적절한 Component
object FieldModule {

    @Provides
    @Singleton
    fun provideFieldRepository(): FieldRepository {
        return FieldRepositoryImpl()
    }

    @Provides
    fun provideFieldUseCase(repository: FieldRepository): GetFieldUseCase {
        return GetFieldUseCase(repository)
    }
}
