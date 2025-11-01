package com.largeblueberry.bitquest.feature_quiz.data

/**
 * 데이터 소스(API, DB 등)로부터 받아오는 퀴즈 데이터의 최상위 클래스입니다.
 * DTO (Data Transfer Object) 역할을 합니다.
 *
 * 이 모델은 외부 데이터 구조에 맞춰져 있으며, domain 계층으로 전달되기 전에
 * domain 계층의 모델로 변환(매핑)됩니다.
 */
sealed class QuizDto {
    abstract val id: Int
    abstract val subject: String
    abstract val question: String
    abstract val explanation: String
}

/**
 * 데이터 소스의 OX 퀴즈 모델 (DTO)
 */
data class OxQuizDto(
    override val id: Int,
    override val subject: String,
    override val question: String,
    override val explanation: String,
    val answer: Boolean
) : QuizDto()

/**
 * 데이터 소스의 선택형 퀴즈 모델 (DTO)
 */
data class MultipleChoiceQuizDto(
    override val id: Int,
    override val subject: String,
    override val question: String,
    override val explanation: String,
    val choices: List<String>,
    val answerIndex: Int
) : QuizDto()
