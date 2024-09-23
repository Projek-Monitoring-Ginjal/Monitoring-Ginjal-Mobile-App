package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import kotlin.random.Random

data class DailyNutrientNeedsInfo(
    val id:Int = Random.nextInt(0, 100000),
    val meals: List<FoodItem> = emptyList(),
    val dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold
)