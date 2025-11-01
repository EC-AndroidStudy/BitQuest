package com.largeblueberry.bitquest.feature_gemini.di

import com.largeblueberry.bitquest.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.largeblueberry.bitquest.feature_gemini.data.GeminiRepositoryImpl
import com.largeblueberry.bitquest.feature_gemini.domain.GeminiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-pro",
            BuildConfig.GEMINI_API_KEY // API 키를 BuildConfig에 설정
        )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGeminiRepository(
        generativeModel: GenerativeModel,
        gson: Gson
    ): GeminiRepository = GeminiRepositoryImpl(generativeModel, gson)
}