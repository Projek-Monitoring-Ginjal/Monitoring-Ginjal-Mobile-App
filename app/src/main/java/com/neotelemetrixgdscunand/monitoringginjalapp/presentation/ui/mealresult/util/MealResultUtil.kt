package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util

import androidx.compose.ui.graphics.Color
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green40
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow40

object MealResultUtil {

    fun calculateProgressFraction(progressAmount:Float, maxAmount:Float):Float{
        val progressFraction = (progressAmount / maxAmount).let {
            if(it > 2f){
                2f
            } else if(it < 0f) {
                0f
            } else it

        }
        return progressFraction
    }

    fun FoodNutrient.determineNutritionPreviewBarColor(): Color {
        return when(this){
            is FoodNutrient.Calorie, is FoodNutrient.Protein -> Green40
            else -> Yellow40
        }
    }

    fun FoodNutrient.determineIsNutritionLessAmountSufficient(): Boolean {
        return when(this){
            is FoodNutrient.Calorie, is FoodNutrient.Protein -> false
            else -> true
        }
    }

    fun Float.roundOffDecimal(): Float {
        return Math.round(this * 100.0) / 100.0f
    }

    fun calculateDailyNutritionAmountAndThreshold(
        dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold,
        nutritionEssentialsForFourDays: List<NutritionEssential>,
        dayOptions: DayOptions
    ):Pair<DailyNutrientNeedsThreshold, NutritionEssential>{
        var deficitOrSurplusCalorie = 0f
        var deficitOrSurplusFluid = 0f
        var deficitOrSurplusProtein = 0f
        var deficitOrSurplusSodium = 0f
        var deficitOrSurplusPotassium = 0f

        val daysBeforeIndex = dayOptions.index - 1
        if(daysBeforeIndex > -1){
            for(i in 0..daysBeforeIndex){
                val currDayNutritionEssential = nutritionEssentialsForFourDays[i]
                deficitOrSurplusCalorie += currDayNutritionEssential.calorie.amount - dailyNutrientNeedsThreshold.caloriesThreshold
                deficitOrSurplusFluid += currDayNutritionEssential.fluid.amount - dailyNutrientNeedsThreshold.fluidThreshold
                deficitOrSurplusProtein += currDayNutritionEssential.protein.amount - dailyNutrientNeedsThreshold.proteinThreshold
                deficitOrSurplusSodium += currDayNutritionEssential.sodium.amount - dailyNutrientNeedsThreshold.sodiumThreshold
                deficitOrSurplusPotassium += currDayNutritionEssential.potassium.amount - dailyNutrientNeedsThreshold.potassiumThreshold
            }
        }

        println("Deficit")
        println(deficitOrSurplusCalorie)
        println(deficitOrSurplusFluid)
        println(deficitOrSurplusProtein)
        println(deficitOrSurplusSodium)
        println(deficitOrSurplusPotassium)

        val currentAdjustedDayNutritionEssential = nutritionEssentialsForFourDays[dayOptions.index].let {
            NutritionEssential(
                calorie = FoodNutrient.Calorie(it.calorie.amount + deficitOrSurplusCalorie),
                fluid = FoodNutrient.Fluid(it.fluid.amount + deficitOrSurplusFluid),
                protein = FoodNutrient.Protein(it.protein.amount + deficitOrSurplusProtein),
                sodium = FoodNutrient.Sodium(it.sodium.amount + deficitOrSurplusSodium),
                potassium = FoodNutrient.Potassium(it.potassium.amount + deficitOrSurplusPotassium)
            )
        }

        currentAdjustedDayNutritionEssential.apply {
            println("amount")
            println(calorie.amount)
            println(fluid.amount)
            println(protein.amount)
            println(sodium.amount)
            println(potassium.amount)
        }

        val currentAdjustedDayNutritionThreshold = nutritionEssentialsForFourDays[dayOptions.index].let {
            DailyNutrientNeedsThreshold(
                caloriesThreshold = dailyNutrientNeedsThreshold.caloriesThreshold - deficitOrSurplusCalorie,
                fluidThreshold = dailyNutrientNeedsThreshold.fluidThreshold - deficitOrSurplusFluid,
                proteinThreshold = dailyNutrientNeedsThreshold.proteinThreshold - deficitOrSurplusProtein,
                sodiumThreshold = dailyNutrientNeedsThreshold.sodiumThreshold - deficitOrSurplusSodium,
                potassiumThreshold = dailyNutrientNeedsThreshold.potassiumThreshold - deficitOrSurplusPotassium
            )
        }

        return Pair(currentAdjustedDayNutritionThreshold, currentAdjustedDayNutritionEssential)
    }
}