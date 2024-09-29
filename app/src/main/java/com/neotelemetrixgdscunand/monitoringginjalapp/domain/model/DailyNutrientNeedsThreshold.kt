package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class DailyNutrientNeedsThreshold(
    val caloriesThreshold: Float = Float.NaN,
    val fluidThreshold: Float = Float.NaN,
    val proteinThreshold: Float = Float.NaN,
    val potassiumThreshold: Float = Float.NaN,
    val sodiumThreshold: Float = Float.NaN,
)