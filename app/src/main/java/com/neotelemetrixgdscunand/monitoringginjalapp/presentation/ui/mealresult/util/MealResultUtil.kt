package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential

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

        val currentAdjustedDayNutritionEssential = nutritionEssentialsForFourDays[dayOptions.index].let {
            NutritionEssential(
                calorie = FoodNutrient.Calorie(it.calorie.amount + deficitOrSurplusCalorie),
                fluid = FoodNutrient.Fluid(it.fluid.amount + deficitOrSurplusFluid),
                protein = FoodNutrient.Protein(it.protein.amount + deficitOrSurplusProtein),
                sodium = FoodNutrient.Sodium(it.sodium.amount + deficitOrSurplusSodium),
                potassium = FoodNutrient.Potassium(it.potassium.amount + deficitOrSurplusPotassium)
            )
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