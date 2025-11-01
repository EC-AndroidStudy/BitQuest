package com.largeblueberry.bitquest.feature_quiz.domain

/**
 * 퀴즈 데이터 모델을 나타내는 최상위 클래스입니다.
 * 모든 퀴즈는 공통적으로 id, 주제, 문제, 해설을 가집니다.
 */
sealed class QuizModel {
    abstract val id: Int
    abstract val subject: String
    abstract val question: String
    abstract val explanation: String
}

/**
 * OX 퀴즈 데이터 모델
 *
 * @param answer 정답 (true/false)
 */
data class OxQuiz(
    override val id: Int,
    override val subject: String,
    override val question: String,
    override val explanation: String,
    val answer: Boolean
) : QuizModel()

/**
 * 선택형 퀴즈 데이터 모델
 *
 * @param choices 4개의 선택지 목록
 * @param answerIndex 정답 선택지의 인덱스 (0부터 시작)
 */
data class MultipleChoiceQuiz(
    override val id: Int,
    override val subject: String,
    override val question: String,
    override val explanation: String,
    val choices: List<String>,
    val answerIndex: Int
) : QuizModel()
