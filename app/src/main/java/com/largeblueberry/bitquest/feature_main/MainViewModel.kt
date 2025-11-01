package com.largeblueberry.bitquest.feature_main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class BitQuestCardData(
    val tier: String,
    val description: String,
    val heartCount: String,
    val title: String,
    val score: String,
    val accuracy: String
)

data class BitQuestUiState(
    val cardData: BitQuestCardData = BitQuestCardData(
        tier = "티어",
        description = "실전 문제를 즐겁게",
        heartCount = "하트 개수",
        title = "BitQuest",
        score = "점수 : 2000점",
        accuracy = "정답률 : 90%"
    ),
    val isLoading: Boolean = false
)

class MainViewModel(

) : ViewModel() {

    private val _uiState = MutableStateFlow(BitQuestUiState())
    val uiState: StateFlow<BitQuestUiState> = _uiState.asStateFlow()

    fun onSolveProblem() {
        // 문제 풀기 로직
        println("문제 풀러 가기 클릭")
    }

    fun onAiAnalysis() {
        // AI 오답 분석 로직
        println("AI 오답 분석 클릭")
    }

    fun onNavigateToMain() {
        // 메인으로 이동 로직
        println("메인 클릭")
    }

    fun onNavigateToMyPage() {
        // 마이페이지 이동 로직
        println("나 클릭")
    }

    fun updateCardData(newData: BitQuestCardData) {
        _uiState.value = _uiState.value.copy(cardData = newData)
    }
}