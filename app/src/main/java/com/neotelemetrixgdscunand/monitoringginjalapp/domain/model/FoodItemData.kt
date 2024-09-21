package com.neotelemetrixgdscunand.monitoringginjalapp.domain.model

data class FoodItemData(
    val foodName: String,
    val calories: String,
    val volume: String,
    val protein: String,
    val sodium: String,
    val potassium: String
)

fun getFoodItems(): List<FoodItemData> {
    return listOf(
        FoodItemData("Nasi Goreng", "625", "200", "8.8", "22.5", "107"),
        FoodItemData("Udang Segar", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Telur Ceplok", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Udang Merah", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Bakso", "625", "50", "8.8", "22.5", "107")
    )
}