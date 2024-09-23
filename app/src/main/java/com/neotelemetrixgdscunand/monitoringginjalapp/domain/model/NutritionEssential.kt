package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class NutritionEssential(
    val calorie: FoodNutrient.Calorie = FoodNutrient.Calorie(),
    val fluid: FoodNutrient.Fluid = FoodNutrient.Fluid(),
    val protein: FoodNutrient.Protein = FoodNutrient.Protein(),
    val natrium: FoodNutrient.Natrium = FoodNutrient.Natrium(),
    val kalium: FoodNutrient.Kalium = FoodNutrient.Kalium()
)