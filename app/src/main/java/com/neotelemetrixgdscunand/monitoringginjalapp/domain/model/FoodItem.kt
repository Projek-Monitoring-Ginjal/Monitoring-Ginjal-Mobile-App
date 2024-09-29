package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import kotlin.random.Random

data class FoodItem(
    val id:Int = Random.nextInt(0, 100000),
    val name:String,
    val nutritionEssential: NutritionEssential,
    val unit:String = "Unit"
)