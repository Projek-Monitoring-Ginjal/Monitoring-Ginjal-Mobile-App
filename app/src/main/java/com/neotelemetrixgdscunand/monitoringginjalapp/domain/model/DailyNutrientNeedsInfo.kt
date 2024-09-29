package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class DailyNutrientNeedsInfo(
    val day:DayOptions,
    val meals: List<FoodItemCart> = emptyList(),
    val dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold
)