package com.largeblueberry.bitquest.di

import com.largeblueberry.bitquest.data.local.QuizLocalDataSource
import com.largeblueberry.bitquest.data.local.QuizLocalDataSourceImpl
import com.largeblueberry.bitquest.data.repository.QuizRepositoryImpl
import com.largeblueberry.bitquest.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * [작업 담당자: Android 개발자 (DI 패턴 이해)]
 *
 * TODO: 저장소 의존성 주입 모듈
 * - Repository 인터페이스와 구현체 바인딩
 * - @Binds 어노테이션 활용
 * - 각 Repository의 의존성 주입
 * - 스코프 설정 (@Singleton)
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ): QuizRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideQuizLocalDataSource(): QuizLocalDataSource {
        return QuizLocalDataSourceImpl()
    }
}