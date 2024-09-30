package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class DailyNutrientNeedsThreshold(
    val caloriesThreshold: Float = 0f,
    val fluidThreshold: Float = 0f,
    val proteinThreshold: Float = 0f,
    val potassiumThreshold: Float = 0f,
    val sodiumThreshold: Float = 0f,
)