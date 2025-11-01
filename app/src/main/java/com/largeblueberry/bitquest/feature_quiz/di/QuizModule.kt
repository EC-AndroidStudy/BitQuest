package com.largeblueberry.bitquest.feature_quiz.di

import android.content.Context
import androidx.room.Room
import com.largeblueberry.bitquest.feature_quiz.data.QuizDao
import com.largeblueberry.bitquest.feature_quiz.data.QuizDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuizDatabase(@ApplicationContext context: Context): QuizDatabase {
        return Room.databaseBuilder(
            context,
            QuizDatabase::class.java,
            "quiz_database"
        ).build()
    }

    @Provides
    @Singleton  // 이것도 추가하는 게 좋습니다
    fun provideQuizDao(database: QuizDatabase): QuizDao {
        return database.quizDao()
    }
}
