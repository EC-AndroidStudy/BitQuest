package com.largeblueberry.bitquest.feature_FieldSelection.domain

class GetFieldUseCase(
    private val repository: FieldRepository
) {
    suspend operator fun invoke(): List<Field> {
        return repository.getField()
    }
}