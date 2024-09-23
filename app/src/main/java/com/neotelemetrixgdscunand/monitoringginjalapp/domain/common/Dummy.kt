package com.neotelemetrixgdscunand.monitoringginjalapp.domain.common

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential

object Dummy {
    fun getFoodItems(): List<FoodItem> {
        return listOf(
            FoodItem(
                name = "Nasi Goreng",
                nutritionEssential = NutritionEssential(
                    calorie = FoodNutrient.Calorie(625f),
                    fluid = FoodNutrient.Fluid(200f),
                    protein = FoodNutrient.Protein(8.8f),
                    natrium = FoodNutrient.Natrium(22.5f),
                    kalium = FoodNutrient.Kalium(107f)
                )
            ),
            FoodItem(
                name = "Udang Segar",
                nutritionEssential = NutritionEssential(
                    calorie = FoodNutrient.Calorie(625f),
                    fluid = FoodNutrient.Fluid(200f),
                    protein = FoodNutrient.Protein(8.8f),
                    natrium = FoodNutrient.Natrium(22.5f),
                    kalium = FoodNutrient.Kalium(107f)
                )
            ),
            FoodItem(
                name = "Telur Ceplok",
                nutritionEssential = NutritionEssential(
                    calorie = FoodNutrient.Calorie(625f),
                    fluid = FoodNutrient.Fluid(200f),
                    protein = FoodNutrient.Protein(8.8f),
                    natrium = FoodNutrient.Natrium(22.5f),
                    kalium = FoodNutrient.Kalium(107f)
                )
            ),
            FoodItem(
                name = "Udang Merah",
                nutritionEssential = NutritionEssential(
                    calorie = FoodNutrient.Calorie(625f),
                    fluid = FoodNutrient.Fluid(200f),
                    protein = FoodNutrient.Protein(8.8f),
                    natrium = FoodNutrient.Natrium(22.5f),
                    kalium = FoodNutrient.Kalium(107f)
                )
            ),
            FoodItem(
                name = "Bakso",
                nutritionEssential = NutritionEssential(
                    calorie = FoodNutrient.Calorie(625f),
                    fluid = FoodNutrient.Fluid(200f),
                    protein = FoodNutrient.Protein(8.8f),
                    natrium = FoodNutrient.Natrium(22.5f),
                    kalium = FoodNutrient.Kalium(107f)
                )
            ),
        )
    }


}

