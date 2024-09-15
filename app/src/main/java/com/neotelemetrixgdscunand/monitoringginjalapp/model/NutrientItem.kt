package com.neotelemetrixgdscunand.monitoringginjalapp.model

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
    const val CALORIE_THRESHOLD = 1800.0
    const val LIQUID_THRESHOLD = 2000.0
    const val PROTEIN_THRESHOLD = 50.0
    const val SODIUM_THRESHOLD = 2300.0
    const val POTASSIUM_THRESHOLD = 3500.0
}

fun getNutrientStatus(value: Double, threshold: Double): NutrientStatus {
    return when {
        value > threshold -> NutrientStatus.BERLEBIH
        value < threshold -> NutrientStatus.BELUM_TERPENUHI
        value == threshold -> NutrientStatus.TERPENUHI
        else -> NutrientStatus.TERPENUHI
    }
}

