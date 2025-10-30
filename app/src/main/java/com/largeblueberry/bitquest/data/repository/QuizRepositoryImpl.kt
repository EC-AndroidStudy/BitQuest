package com.largeblueberry.bitquest.data.repository

import com.largeblueberry.bitquest.data.local.QuizLocalDataSource
import com.largeblueberry.bitquest.domain.model.Quiz
import com.largeblueberry.bitquest.domain.repository.QuizRepository
import javax.inject.Inject
/**
 * [작업 담당자: Android 개발자 (Repository 구현 경험)]
 *
 * TODO: 퀴즈 저장소 구현체
 * - 로컬 DB와 원격 API 데이터 조합
 * - 캐시 우선 로딩 전략 구현
 * - 오프라인 지원 로직
 * - 데이터 동기화 처리
 * - 에러 처리 및 fallback 로직
 * - Paging3 연동
 */
class QuizRepositoryImpl @Inject constructor(
    private val localDataSource: QuizLocalDataSource
) : QuizRepository {
    override fun getQuizzes(): List<Quiz> {
        return localDataSource.getQuizzes()
    }
}