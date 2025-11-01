package com.largeblueberry.bitquest

import android.app.Application
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.model.WrongAnswerNote
import com.largeblueberry.bitquest.feature_WrongAnswerNote.domain.repository.WrongAnswerRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class BitQuestApplication : Application() {

    @Inject
    lateinit var wrongAnswerRepository: WrongAnswerRepository

    override fun onCreate() {
        super.onCreate()
        seedInitialData()
    }

    private fun seedInitialData() {
        CoroutineScope(Dispatchers.IO).launch {
            if (wrongAnswerRepository.observeAll().first().isEmpty()) {
                // WrongAnswerNote (도메인 모델)을 생성하여 전달합니다.
                wrongAnswerRepository.addAll(
                    listOf(
                        WrongAnswerNote(
                            questionId = 2,
                            title = "AndroidViewModel의 한계",
                            questionText = "'AndroidViewModel'을 상속받아 'Context'를 사용하는 것은, 단위 테스트(Unit Test) 시 'Context'를 Mocking해야 하는 복잡성을 야기하므로 권장되지 않는다.",
                            selectedAnswer = "X", // 틀린 답
                            correctAnswer = "O", // 정답
                            category = "Android",
                            createdAt = System.currentTimeMillis()
                        )
                    )
                )
            }
        }
    }
}