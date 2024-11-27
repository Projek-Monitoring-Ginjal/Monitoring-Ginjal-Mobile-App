package com.neotelemetrixgdscunand.monitoringginjalapp.data.repository

import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.FoodContentItemResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.GetFoodCartResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.GetHemodialisaResultResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.NutritionThresholdResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemCart
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodPortionOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential

object Mapper {

    fun FoodItemCart.mapToFoodItemBody():String{
        /*return FoodItemBody(
            this.id,
            portion = this.portionOptions.multiplier
        )*/
        return """{"makanan_id": ${this.foodItem.id}, "portion": ${this.portionOptions.multiplier}}""".trimIndent()
    }

    fun GetHemodialisaResultResponse.mapToMealResultInfo(hemodialisaType: HemodialisaType):Pair<List<DailyNutrientNeedsThreshold?>, List<NutritionEssential?>>{
        val listNutritionResults:MutableList<NutritionEssential?> = mutableListOf(
            this.countNutritionDay1?.mapToNutritionEssential(),
            this.countNutritionDay2?.mapToNutritionEssential(),
            this.countNutritionDay3?.mapToNutritionEssential(),
        )

        if(hemodialisaType == HemodialisaType.HEMODIALISA_2){
            listNutritionResults.add(
                this.countNutritionDay4?.mapToNutritionEssential(),
            )
        }
        val listDailyNutrientNeedsThreshold = mutableListOf(
            this.nutritionThresholdDay1?.run {
                DailyNutrientNeedsThreshold(
                    caloriesThreshold = calories ?: 0f,
                    fluidThreshold = fluids ?: 0f,
                    proteinThreshold = protein ?: 0f,
                    sodiumThreshold = sodium ?: 0f,
                    potassiumThreshold = potassium ?:0f
                )
            },
            this.nutritionThresholdDay2?.run {
                DailyNutrientNeedsThreshold(
                    caloriesThreshold = calories ?: 0f,
                    fluidThreshold = fluids ?: 0f,
                    proteinThreshold = protein ?: 0f,
                    sodiumThreshold = sodium ?: 0f,
                    potassiumThreshold = potassium ?:0f
                )
            },
            this.nutritionThresholdDay3?.run {
                DailyNutrientNeedsThreshold(
                    caloriesThreshold = calories ?: 0f,
                    fluidThreshold = fluids ?: 0f,
                    proteinThreshold = protein ?: 0f,
                    sodiumThreshold = sodium ?: 0f,
                    potassiumThreshold = potassium ?:0f
                )
            }
        )

        if(hemodialisaType == HemodialisaType.HEMODIALISA_2){
            listDailyNutrientNeedsThreshold.add(
                this.nutritionThresholdDay4?.run {
                    DailyNutrientNeedsThreshold(
                        caloriesThreshold = calories ?: 0f,
                        fluidThreshold = fluids ?: 0f,
                        proteinThreshold = protein ?: 0f,
                        sodiumThreshold = sodium ?: 0f,
                        potassiumThreshold = potassium ?:0f
                    )
                }
            )
        }


        return Pair(listDailyNutrientNeedsThreshold, listNutritionResults)
    }

    fun NutritionThresholdResponse.mapToNutritionEssential():NutritionEssential{
        return NutritionEssential(
            calorie = FoodNutrient.Calorie(this.calories ?: 0f),
            fluid = FoodNutrient.Fluid(this.fluids ?: 0f),
            protein = FoodNutrient.Protein(this.protein ?: 0f),
            sodium = FoodNutrient.Sodium(this.sodium ?: 0f),
            potassium = FoodNutrient.Potassium(this.potassium ?: 0f)
        )
    }

    fun FoodContentItemResponse.mapToFoodItem():FoodItem{
        return FoodItem(
            id = foodId ?: throw Exception("error.."),
            name = this.name ?: "",
            unit = this.satuan ?: "Unit",
            nutritionEssential = NutritionEssential(
                calorie = FoodNutrient.Calorie(this.energy ?: 0f),
                fluid = FoodNutrient.Fluid(this.water ?: 0f),
                protein = FoodNutrient.Protein(this.protein ?: 0f),
                sodium = FoodNutrient.Sodium(this.sodium ?: 0f),
                potassium = FoodNutrient.Potassium(this.potassium ?: 0f)
            )
        )
    }

    fun GetFoodCartResponse.mapToDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ):DailyNutrientNeedsInfo{
        val foodItemCarts = if(foodCart.isNullOrEmpty()){
            emptyList()
        }else{
            this.foodCart.map {
                FoodItemCart(
                    foodItem = FoodItem(
                        unit = it.makanan?.satuan ?: "Unit",
                        id = it.makananId ?: throw Exception("error..."),
                        name = it.makanan?.name ?: "",
                        nutritionEssential = NutritionEssential(
                            calorie = FoodNutrient.Calorie(it.makanan?.energy ?: 0f),
                            fluid = FoodNutrient.Fluid(it.makanan?.water ?: 0f),
                            protein = FoodNutrient.Protein(it.makanan?.protein ?: 0f),
                            sodium = FoodNutrient.Sodium(it.makanan?.sodium ?: 0f),
                            potassium = FoodNutrient.Potassium(it.makanan?.potassium?:0f)
                        )
                    ),
                    portionOptions = FoodPortionOptions.entries.first { en -> en.multiplier ==  (it.portion?:1f) }
                )
            }
        }


        return DailyNutrientNeedsInfo(
            day = dayOptions,
            meals = foodItemCarts,
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold(
                sodiumThreshold = this.nutritionTreshold?.sodium ?: 0f,
                potassiumThreshold = this.nutritionTreshold?.potassium ?: 0f,
                proteinThreshold = this.nutritionTreshold?.protein ?: 0f,
                caloriesThreshold = this.nutritionTreshold?.calories ?: 0f,
                fluidThreshold = this.nutritionTreshold?.fluids ?: 0f
            )
        )
    }
}