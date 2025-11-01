package com.largeblueberry.bitquest.feature_gemini.data

import com.google.ai.client.generativeai.GenerativeModel
import com.google.gson.Gson
import com.largeblueberry.bitquest.feature_gemini.AnalysisResult
import com.largeblueberry.bitquest.feature_gemini.domain.GeminiRepository
import com.largeblueberry.bitquest.feature_gemini.WrongAnswer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiRepositoryImpl @Inject constructor(
    private val generativeModel: GenerativeModel,
    private val gson: Gson
) : GeminiRepository {

    override suspend fun analyzeWrongAnswers(wrongAnswers: List<WrongAnswer>): Result<AnalysisResult> {
        return try {
            val prompt = buildAnalysisPrompt(wrongAnswers)
            val response = generativeModel.generateContent(prompt)
            val analysisText = response.text ?: throw Exception("응답이 비어있습니다")

            // JSON 파싱 시도
            val analysisResult = parseAnalysisResponse(analysisText)
            Result.success(analysisResult)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun buildAnalysisPrompt(wrongAnswers: List<WrongAnswer>): String {
        val wrongAnswersJson = gson.toJson(wrongAnswers)

        return """
        다음은 사용자의 CS 퀴즈 오답 내역입니다:
        
        $wrongAnswersJson
        
        이 데이터를 분석하여 다음 형식의 JSON으로 응답해주세요:
        
        {
            "weakAreas": ["약한 분야1", "약한 분야2", "약한 분야3"],
            "recommendations": ["추천사항1", "추천사항2", "추천사항3"],
            "studyPlan": "구체적인 학습 계획 설명",
            "overallScore": 점수(0-100),
            "detailedFeedback": "상세한 피드백 설명"
        }
        
        분석 기준:
        1. 카테고리별 오답률 분석
        2. 난이도별 성취도 분석  
        3. 자주 틀리는 개념 파악
        4. 개선 방향 제시
        
        한국어로 응답해주세요.
        """.trimIndent()
    }

    private fun parseAnalysisResponse(response: String): AnalysisResult {
        return try {
            // JSON 부분만 추출
            val jsonStart = response.indexOf("{")
            val jsonEnd = response.lastIndexOf("}") + 1
            val jsonString = response.substring(jsonStart, jsonEnd)

            gson.fromJson(jsonString, AnalysisResult::class.java)
        } catch (e: Exception) {
            // JSON 파싱 실패 시 기본 분석 결과 반환
            AnalysisResult(
                weakAreas = listOf("분석 중 오류 발생"),
                recommendations = listOf("다시 시도해주세요"),
                studyPlan = "분석을 다시 진행해주세요",
                overallScore = 0,
                detailedFeedback = response
            )
        }
    }
}