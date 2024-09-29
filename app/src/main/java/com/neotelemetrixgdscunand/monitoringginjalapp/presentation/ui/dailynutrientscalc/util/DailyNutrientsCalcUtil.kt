package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.util

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold

object DailyNutrientsCalcUtil {
    fun calculateDailyFoodNeeds(weight:Float):DailyNutrientNeedsThreshold{
        val calorie = (weight * 35)
        val fluid = (weight * 0.8f + 500)
        val kalium = 2500f
        val natrium = 200f
        val protein = (weight * 1.2f)
        return DailyNutrientNeedsThreshold(
            caloriesThreshold = calorie,
            fluidThreshold = fluid,
            proteinThreshold = protein,
            potassiumThreshold = kalium,
            sodiumThreshold = natrium
        )
    }
}