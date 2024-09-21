package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class NutrientItem(
    val name: String,
    val value: Double,
    val unit: String,
    val status: NutrientStatus
)

enum class NutrientStatus {
    BELUM_TERPENUHI,
    TERPENUHI,
    BERLEBIH
}

object NutrientThresholds {
    var CALORIE_THRESHOLD = 1800.0
    var LIQUID_THRESHOLD = 300.0
    var PROTEIN_THRESHOLD = 50.0
    var SODIUM_THRESHOLD = 2300.0
    var POTASSIUM_THRESHOLD = 3500.0

    fun updateThresholds(calories: Double, liquid: Double, protein: Double, sodium: Double, potassium: Double) {
        CALORIE_THRESHOLD = calories
        LIQUID_THRESHOLD = liquid
        PROTEIN_THRESHOLD = protein
        SODIUM_THRESHOLD = sodium
        POTASSIUM_THRESHOLD = potassium
    }
}

fun getNutrientStatus(value: Double, threshold: Double): NutrientStatus {
    return when {
        value > threshold -> NutrientStatus.BERLEBIH
        value < threshold -> NutrientStatus.BELUM_TERPENUHI
        value == threshold -> NutrientStatus.TERPENUHI
        else -> NutrientStatus.TERPENUHI
    }
}

fun getNutrientItems(currentFoodItems: List<FoodItemData>): List<NutrientItem> {
    val totalCalories = currentFoodItems.sumOf { it.calories.toDouble() }
    val totalLiquid = currentFoodItems.sumOf { it.volume.toDouble() }
    val totalProtein = currentFoodItems.sumOf { it.protein.toDouble() }
    val totalSodium = currentFoodItems.sumOf { it.sodium.toDouble() }
    val totalPotassium = currentFoodItems.sumOf { it.potassium.toDouble() }

    return listOf(
        NutrientItem(
            name = "Kalori",
            value = totalCalories,
            unit = "kkal",
            status = getNutrientStatus(totalCalories, NutrientThresholds.CALORIE_THRESHOLD)
        ),
        NutrientItem(
            name = "Cairan",
            value = totalLiquid,
            unit = "ml",
            status = getNutrientStatus(totalLiquid, NutrientThresholds.LIQUID_THRESHOLD)
        ),
        NutrientItem(
            name = "Protein",
            value = totalProtein,
            unit = "gr",
            status = getNutrientStatus(totalProtein, NutrientThresholds.PROTEIN_THRESHOLD)
        ),
        NutrientItem(
            name = "Natrium",
            value = totalSodium,
            unit = "mg",
            status = getNutrientStatus(totalSodium, NutrientThresholds.SODIUM_THRESHOLD)
        ),
        NutrientItem(
            name = "Kalium",
            value = totalPotassium,
            unit = "mg",
            status = getNutrientStatus(totalPotassium, NutrientThresholds.POTASSIUM_THRESHOLD)
        )
    )
}


