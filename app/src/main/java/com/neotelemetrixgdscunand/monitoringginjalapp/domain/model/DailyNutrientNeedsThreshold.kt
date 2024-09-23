package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class DailyNutrientNeedsThreshold(
    val caloriesThreshold: Float = 0f,
    val fluidThreshold: Float = 0f,
    val proteinThreshold: Float = 0f,
    val kaliumThreshold: Float = 0f,
    val natriumThreshold: Float = 0f
)