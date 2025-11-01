package com.largeblueberry.bitquest.feature_FieldSelection.data

import com.largeblueberry.bitquest.feature_FieldSelection.domain.Field
import com.largeblueberry.bitquest.feature_FieldSelection.domain.FieldRepository

class FieldRepositoryImpl : FieldRepository {
    override suspend fun getField(): List<Field> {
        // 해커톤용 하드코딩 데이터
        return listOf(
            FieldEntity(1, "기초 프로그래밍", "변수, 조건문, 반복문", "#4CAF50", 3, 10),
            FieldEntity(2, "안드로이드", "Activity, Compose UI, Architecture", "#2196F3",  1, 8),
            FieldEntity(3, "Git", "merge, pull request, conflict", "#9C27B0", 0, 12),
            FieldEntity(4, "객체지향", "클래스, 상속, 다형성", "#FF9800",  0, 6),
            FieldEntity(5, "데이터베이스", "SQL, 관계형 DB", "#F44336",  0, 15),
            FieldEntity(6, "네트워크", "HTTP, API, 통신", "#795548",  0, 10)
        ).map { it.toDomain() }
    }

    fun FieldEntity.toDomain() = Field(
        id = id,
        title = title,
        description = description,
        colorHex = colorHex,
        completedQuizCount = completedQuizCount,
        totalQuizCount = totalQuizCount
    )

}