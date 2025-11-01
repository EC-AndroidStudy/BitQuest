package com.largeblueberry.bitquest

import android.app.Application
import com.largeblueberry.bitquest.feature_quiz.data.repository.QuizRepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class BitQuestApplication : Application() {

    @Inject
    lateinit var quizRepository: QuizRepositoryImpl

    // Application 레벨 코루틴 스코프
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()

        // 앱 시작시 퀴즈 데이터 초기화
        applicationScope.launch {
            quizRepository.initializeQuizData()
        }
    }
}