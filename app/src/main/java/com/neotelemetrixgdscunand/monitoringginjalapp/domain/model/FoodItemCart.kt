package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

import kotlin.random.Random

data class FoodItemCart(
    val id:Int = Random.nextInt(1, 100000),
    val foodItem: FoodItem,
    val portionOptions: FoodPortionOptions
)