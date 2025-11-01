package com.largeblueberry.bitquest.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * [작업 담당자: Android 개발자]
 *
 * TODO: 퀴즈 결과 화면
 *
 * ResultViewModel.kt:
 * - 퀴즈 결과 계산 및 저장
 * - 통계 데이터 생성
 * - 성취도 분석 (강점/약점 분야)
 * - 추천 퀴즈 로직
 */

// UI 상태를 정의하는 데이터 클래스
data class ResultUiState(
    val correctAnswers: Int = 0,
    val totalQuestions: Int = 0,
    val score: Int = 0,
    val isLoading: Boolean = false
)

// ViewModel 생성 (Hilt)
class ResultViewModel :
    ViewModel() {

    // UI 상태를 보관하는 StateFlow 생성
    // private: ViewModel 내부에서만 수정 가능
    private val _uiState = MutableStateFlow(ResultUiState())
    // public: UI(Composable)에서는 읽기 전용(asStateFlow)으로 노출
    val uiState = _uiState.asStateFlow()

    init {
        // ViewModel이 생성될 때 퀴즈 결과를 불러옴
        loadQuizResult()
    }

    private fun loadQuizResult() {
        viewModelScope.launch {
            // TODO: (예시) 이전 화면이나 Repository에서 실제 퀴즈 결과를 가져옵니다.
            // 이 예시에서는 임시로 하드코딩
            val correct = 8
            val total = 10
            val calculatedScore = (correct * 100) / total

            _uiState.update { currentState ->
                currentState.copy(
                    correctAnswers = correct,
                    totalQuestions = total,
                    score = calculatedScore
                )
            }
        }
    }

    // UI(Composable)로부터 이벤트를 받는 함수
    fun onEvent(event: ResultEvent) {
        when (event) {
            ResultEvent.OnRetryClick -> {
                // TODO: '다시 풀기' 로직 구현 (예: 퀴즈 화면으로 다시 이동)
            }
            ResultEvent.OnReviewClick -> {
                // TODO: '틀린 문제 리뷰' 로직 구현
            }
            ResultEvent.OnShareClick -> {
                // TODO: '결과 공유' 로직 구현 (Intent 사용 등)
            }
            else -> {}
        }
    }
}