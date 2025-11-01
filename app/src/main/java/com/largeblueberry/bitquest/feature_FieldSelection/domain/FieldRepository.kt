package com.largeblueberry.bitquest.feature_FieldSelection.domain

interface FieldRepository {
    suspend fun getField(): List<Field>

}