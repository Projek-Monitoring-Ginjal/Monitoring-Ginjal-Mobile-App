package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.util

import androidx.compose.ui.graphics.Color
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemCart
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green50
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Grey50
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Yellow40

object ListFoodnDrinkUtil {

    fun DailyNutrientNeedsInfo.adjustWithAccumulationOtherDays(
        nutritionEssentialsForFourDays: List<NutritionEssential>,
        dayOptions: DayOptions
    ):DailyNutrientNeedsInfo{

        val dailyNutrientNeedsThreshold = dailyNutrientNeedsThreshold

        /*var deficitOrSurplusCalorie = 0f
        var deficitOrSurplusFluid = 0f
        var deficitOrSurplusProtein = 0f
        var deficitOrSurplusSodium = 0f
        var deficitOrSurplusPotassium = 0f

        val daysBeforeIndex = dayOptions.index - 1
        if(daysBeforeIndex > -1){
            for(i in 0..daysBeforeIndex){
                val currDayNutritionEssential = nutritionEssentialsForFourDays[i]
                //deficitOrSurplusCalorie += currDayNutritionEssential.calorie.amount - dailyNutrientNeedsThreshold.caloriesThreshold
                deficitOrSurplusFluid += (currDayNutritionEssential.fluid.amount - dailyNutrientNeedsThreshold.fluidThreshold).let { if(it < 0f) 0f else it }
                //deficitOrSurplusProtein += currDayNutritionEssential.protein.amount - dailyNutrientNeedsThreshold.proteinThreshold
                deficitOrSurplusSodium += (currDayNutritionEssential.sodium.amount - dailyNutrientNeedsThreshold.sodiumThreshold).let { if(it < 0f) 0f else it }
                deficitOrSurplusPotassium += (currDayNutritionEssential.potassium.amount - dailyNutrientNeedsThreshold.potassiumThreshold).let { if(it < 0f) 0f else it }
            }
        }*/

        val currentAdjustedDayNutritionThreshold = nutritionEssentialsForFourDays[dayOptions.index].let {
            DailyNutrientNeedsThreshold(
                caloriesThreshold = dailyNutrientNeedsThreshold.caloriesThreshold/* - deficitOrSurplusCaloriem*/,
                fluidThreshold = dailyNutrientNeedsThreshold.fluidThreshold/* - deficitOrSurplusFluidm*/,
                proteinThreshold = dailyNutrientNeedsThreshold.proteinThreshold /*- deficitOrSurplusProteinm*/,
                sodiumThreshold = dailyNutrientNeedsThreshold.sodiumThreshold/* - deficitOrSurplusSodiumm*/,
                potassiumThreshold = dailyNutrientNeedsThreshold.potassiumThreshold/* - deficitOrSurplusPotassium*/
            )
        }
        val adjustedDailyNutrientNeedsInfo = this.copy(
            dailyNutrientNeedsThreshold = currentAdjustedDayNutritionThreshold
        )

        return adjustedDailyNutrientNeedsInfo
    }


    fun sumFoodNutritions(
        meals:List<FoodItemCart>,
        dayOptions: DayOptions,
        nutritionEssentialsForFourDays: List<NutritionEssential?>,
        listDailyNutrientNeedsThreshold: List<DailyNutrientNeedsThreshold?>,
    ): NutritionEssential {

        /*var deficitOrSurplusCalorie = 0f
        var deficitOrSurplusFluid = 0f
        var deficitOrSurplusProtein = 0f
        var deficitOrSurplusSodium = 0f
        var deficitOrSurplusPotassium = 0f*/


        var fluidDeficitOrSurplusDaysBefore = 0f
        var sodiumDeficitOrSurplusDaysBefore = 0f
        var potassiumDeficitOrSurplusDaysBefore = 0f

        val daysBeforeIndex = dayOptions.index - 1
        if (daysBeforeIndex > -1) {
            for (i in 0..daysBeforeIndex) {
                val currDayNutritionEssential = nutritionEssentialsForFourDays[i] ?: continue
                val currDayNutrientNeedsThreshold = listDailyNutrientNeedsThreshold[i] ?: continue
                //deficitOrSurplusCalorie += currDayNutritionEssential.calorie.amount - dailyNutrientNeedsThreshold.caloriesThreshold
                (currDayNutritionEssential.fluid.amount - currDayNutrientNeedsThreshold.fluidThreshold).let {
                    fluidDeficitOrSurplusDaysBefore += it
                    if (fluidDeficitOrSurplusDaysBefore < 0f) {
                        fluidDeficitOrSurplusDaysBefore = 0f
                    }
                }
                //deficitOrSurplusProtein += currDayNutritionEssential.protein.amount - dailyNutrientNeedsThreshold.proteinThreshold
                (currDayNutritionEssential.sodium.amount - currDayNutrientNeedsThreshold.sodiumThreshold).let {
                    sodiumDeficitOrSurplusDaysBefore += it
                    if (sodiumDeficitOrSurplusDaysBefore < 0f) {
                        sodiumDeficitOrSurplusDaysBefore = 0f
                    }
                }
                (currDayNutritionEssential.potassium.amount - currDayNutrientNeedsThreshold.potassiumThreshold).let {
                    potassiumDeficitOrSurplusDaysBefore += it
                    if (potassiumDeficitOrSurplusDaysBefore < 0f) {
                        potassiumDeficitOrSurplusDaysBefore = 0f
                    }
                }
            }
        }

            var totalCalories = 0f
            var totalFluid = 0f
            var totalProtein = 0f
            var totalNatrium = 0f
            var totalKalium = 0f

            meals.forEach {
                totalCalories += it.foodItem.nutritionEssential.calorie.amount
                totalFluid += it.foodItem.nutritionEssential.fluid.amount
                totalProtein += it.foodItem.nutritionEssential.protein.amount
                totalNatrium += it.foodItem.nutritionEssential.sodium.amount
                totalKalium += it.foodItem.nutritionEssential.potassium.amount
            }

            val currentAdjustedDayNutritionEssential = NutritionEssential(
                calorie = FoodNutrient.Calorie(totalCalories/* + deficitOrSurplusCalorie*/),
                fluid = FoodNutrient.Fluid(totalFluid + fluidDeficitOrSurplusDaysBefore),
                protein = FoodNutrient.Protein(totalProtein/* + deficitOrSurplusProtein*/),
                sodium = FoodNutrient.Sodium(totalNatrium + sodiumDeficitOrSurplusDaysBefore),
                potassium = FoodNutrient.Potassium(totalKalium + potassiumDeficitOrSurplusDaysBefore)
            )

            return currentAdjustedDayNutritionEssential
        }


    fun getColorFromGradient(
        fraction: Float,
        gradient: List<Pair<Color, Float>> = listOf(
            Grey50 to 0f,
            Green50 to 1f,
            Yellow40 to 2f
        )
    ): Color {
        if (gradient.isEmpty()) return Color.Transparent

        val normalizedFraction = fraction.coerceIn(
            gradient.first().second, gradient.last().second
        )

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