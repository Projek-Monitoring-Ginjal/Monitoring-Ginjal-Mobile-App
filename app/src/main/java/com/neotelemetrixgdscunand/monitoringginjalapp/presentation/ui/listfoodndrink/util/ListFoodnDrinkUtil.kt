package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.util

import androidx.compose.ui.graphics.Color
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green50
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey50
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow40

object ListFoodnDrinkUtil {

    fun sumFoodNutritions(meals:List<FoodItem>): NutritionEssential {
        var totalCalories = 0f
        var totalFluid = 0f
        var totalProtein = 0f
        var totalNatrium = 0f
        var totalKalium = 0f

        meals.forEach {
            totalCalories += it.nutritionEssential.calorie.amount
            totalFluid += it.nutritionEssential.fluid.amount
            totalProtein += it.nutritionEssential.protein.amount
            totalNatrium += it.nutritionEssential.natrium.amount
            totalKalium += it.nutritionEssential.kalium.amount
        }

        return NutritionEssential(
            calorie = FoodNutrient.Calorie(totalCalories),
            fluid = FoodNutrient.Fluid(totalFluid),
            protein = FoodNutrient.Protein(totalProtein),
            natrium = FoodNutrient.Natrium(totalNatrium),
            kalium = FoodNutrient.Kalium(totalKalium)
        )
    }

    fun getColorFromGradient(
        fraction: Float,
        gradient: List<Pair<Color, Float>> = listOf(
            Grey50 to 0f,
            Green50 to 0.75f,
            Yellow40 to 1.5f
        )
    ): Color {
        if (gradient.isEmpty()) return Color.Transparent


        val normalizedFraction = fraction.coerceIn(0f, 1f)

        for (i in 0 until gradient.size - 1) {
            val (startColor, startFraction) = gradient[i]
            val (endColor, endFraction) = gradient[i + 1]

            if (normalizedFraction in startFraction..endFraction) {
                val segmentFraction = (normalizedFraction - startFraction) / (endFraction - startFraction)
                return lerpColor(startColor, endColor, segmentFraction)
            }
        }

        return gradient.last().first
    }

    private fun lerpColor(startColor: Color, endColor: Color, fraction: Float): Color {
        val red = lerp(startColor.red, endColor.red, fraction)
        val green = lerp(startColor.green, endColor.green, fraction)
        val blue = lerp(startColor.blue, endColor.blue, fraction)
        return Color(red, green, blue)
    }

    private fun lerp(start: Float, end: Float, fraction: Float): Float {
        return start + (end - start) * fraction
    }
}