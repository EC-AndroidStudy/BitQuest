package com.largeblueberry.bitquest.feature_WrongAnswerNote.di

import android.content.Context
import androidx.room.Room
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerDao
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.WrongAnswerDatabase
import com.largeblueberry.bitquest.feature_WrongAnswerNote.data.repository.WrongAnswerRepositoryImpl
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WrongAnswerNoteModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): WrongAnswerDatabase {
        return Room.databaseBuilder(
            ctx,
            WrongAnswerDatabase::class.java,
            "bitquest_wrongnote.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDao(db: WrongAnswerDatabase): WrongAnswerDao = db.wrongAnswerDao()

    @Provides
    @Singleton
    fun provideRepository(impl: WrongAnswerRepositoryImpl): WrongAnswerRepository = impl
}