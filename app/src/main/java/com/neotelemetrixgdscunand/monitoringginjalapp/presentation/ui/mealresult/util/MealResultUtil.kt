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
        dailyNutrientNeedsThresholdFourDays: List<DailyNutrientNeedsThreshold?>,
        nutritionEssentialsForFourDays: List<NutritionEssential?>,
        dayOptions: DayOptions
    ):Pair<DailyNutrientNeedsThreshold, NutritionEssential>?{

        val currentNutritionEssential = nutritionEssentialsForFourDays[dayOptions.index] ?: return null
        val currentNutrientNeedsThreshold = dailyNutrientNeedsThresholdFourDays[dayOptions.index] ?: return null

        var fluidDeficitOrSurplusDaysBefore = 0f
        var sodiumDeficitOrSurplusDaysBefore = 0f
        var potassiumDeficitOrSurplusDaysBefore = 0f

        val daysBeforeIndex = dayOptions.index - 1
        if(daysBeforeIndex > -1){

            for(i in 0..daysBeforeIndex){
                val currDayNutritionEssential = nutritionEssentialsForFourDays[i] ?: continue
                val currDayNutrientNeedsThreshold = dailyNutrientNeedsThresholdFourDays[i] ?: continue

                //deficitOrSurplusCalorie += currDayNutritionEssential.calorie.amount - dailyNutrientNeedsThreshold.caloriesThreshold
                (currDayNutritionEssential.fluid.amount - currDayNutrientNeedsThreshold.fluidThreshold)
                    .let {
                        fluidDeficitOrSurplusDaysBefore += it
                        if(fluidDeficitOrSurplusDaysBefore < 0f){
                            fluidDeficitOrSurplusDaysBefore = 0f
                        }
                    }
                //deficitOrSurplusProtein += currDayNutritionEssential.protein.amount - dailyNutrientNeedsThreshold.proteinThreshold
                (currDayNutritionEssential.sodium.amount - currDayNutrientNeedsThreshold.sodiumThreshold).let {
                    sodiumDeficitOrSurplusDaysBefore += it
                    if(sodiumDeficitOrSurplusDaysBefore < 0f){
                        sodiumDeficitOrSurplusDaysBefore = 0f
                    }
                }
                (currDayNutritionEssential.potassium.amount - currDayNutrientNeedsThreshold.potassiumThreshold).let {
                    potassiumDeficitOrSurplusDaysBefore += it
                    if(potassiumDeficitOrSurplusDaysBefore < 0f){
                        potassiumDeficitOrSurplusDaysBefore = 0f
                    }
                }
            }


        }


        val currentAdjustedDayNutritionEssential = currentNutritionEssential.let {
            NutritionEssential(
                calorie = FoodNutrient.Calorie(it.calorie.amount /*+ deficitOrSurplusCalorie*/),
                fluid = FoodNutrient.Fluid(it.fluid.amount + fluidDeficitOrSurplusDaysBefore),
                protein = FoodNutrient.Protein(it.protein.amount /*+ deficitOrSurplusProtein*/),
                sodium = FoodNutrient.Sodium(it.sodium.amount + sodiumDeficitOrSurplusDaysBefore),
                potassium = FoodNutrient.Potassium(it.potassium.amount + potassiumDeficitOrSurplusDaysBefore)
            )
        }
        

        val currentAdjustedDayNutritionThreshold = DailyNutrientNeedsThreshold(
            caloriesThreshold = currentNutrientNeedsThreshold.caloriesThreshold/* - deficitOrSurplusCalorie*/,
            fluidThreshold = currentNutrientNeedsThreshold.fluidThreshold /*- deficitOrSurplusFluid*/,
            proteinThreshold = currentNutrientNeedsThreshold.proteinThreshold/* - deficitOrSurplusProtein*/,
            sodiumThreshold = currentNutrientNeedsThreshold.sodiumThreshold/* - deficitOrSurplusSodium*/,
            potassiumThreshold = currentNutrientNeedsThreshold.potassiumThreshold/* - deficitOrSurplusPotassium*/
        )

        return Pair(currentAdjustedDayNutritionThreshold, currentAdjustedDayNutritionEssential)
    }
}