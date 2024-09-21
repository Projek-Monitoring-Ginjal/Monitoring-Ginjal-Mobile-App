package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemData
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getNutrientItems
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListFoodnDrinkViewModel @Inject constructor() : ViewModel() {
    var currentFoodItems = mutableStateOf<List<FoodItemData>>(emptyList())
        private set

    var nutrientItems = mutableStateOf(getNutrientItems(currentFoodItems.value))
        private set

    var showPortionDialog = mutableStateOf<FoodItemData?>(null)
        private set

    fun addFoodItem(foodItem: FoodItemData) {
        currentFoodItems.value = currentFoodItems.value.toMutableList().apply {
            add(foodItem)
        }
        updateNutrients()


    }

    fun deleteFoodItem(foodItem: FoodItemData) {
        currentFoodItems.value = currentFoodItems.value.toMutableList().apply {
            remove(foodItem)
        }
        updateNutrients()
    }


    fun showPortionDialog(foodItem: FoodItemData) {
        showPortionDialog.value = foodItem
    }

    fun dismissPortionDialog() {
        showPortionDialog.value = null
    }

    fun updateFoodItem(portion: String, foodItem: FoodItemData) {
        val multiplier = when (portion) {
            "1 piring" -> 1.0
            "1/2 piring" -> 0.5
            "1/4 piring" -> 0.25
            else -> 1.0
        }

        val adjustedFoodItem = foodItem.copy(
            calories = (foodItem.calories.toDouble() * multiplier).toString(),
            volume = (foodItem.volume.toDouble() * multiplier).toString(),
            protein = (foodItem.protein.toDouble() * multiplier).toString(),
            sodium = (foodItem.sodium.toDouble() * multiplier).toString(),
            potassium = (foodItem.potassium.toDouble() * multiplier).toString()
        )

        currentFoodItems.value = currentFoodItems.value.toMutableList().apply {
            add(adjustedFoodItem)
        }
        updateNutrients()
    }

    private fun updateNutrients() {
        nutrientItems.value = getNutrientItems(currentFoodItems.value)
    }

}
