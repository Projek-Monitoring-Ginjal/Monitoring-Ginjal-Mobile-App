package com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotelemetrixgdscunand.monitoringginjalapp.model.NutrientItem
import com.neotelemetrixgdscunand.monitoringginjalapp.model.NutrientThresholds
import com.neotelemetrixgdscunand.monitoringginjalapp.model.getNutrientStatus
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog


@SuppressLint("MutableCollectionMutableState")
@Composable
fun ListFoodnDrinkScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    initialFoodItems: List<FoodItemData>,
    onDeleteClick: (FoodItemData) -> Unit
) {
    var currentFoodItems by remember { mutableStateOf(initialFoodItems.toMutableList()) }
    var showPortionDialog by remember { mutableStateOf<FoodItemData?>(null) }

    val totalCalories = currentFoodItems.sumOf { it.calories.toDouble() }
    val totalLiquid = currentFoodItems.sumOf { it.volume.toDouble() }
    val totalProtein = currentFoodItems.sumOf { it.protein.toDouble() }
    val totalSodium = currentFoodItems.sumOf { it.sodium.toDouble() }
    val totalPotassium = currentFoodItems.sumOf { it.potassium.toDouble() }

    val nutrientItems = listOf(
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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Toolbar(onBackClick = onBackClick)

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            searchFoodItems = initialFoodItems,
            onAddClick = { foodItem ->
                showPortionDialog = foodItem
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1f)
        ) {
            items(currentFoodItems) { food ->
                FoodItem(
                    food = food,
                    onDeleteClick = {
                        currentFoodItems = currentFoodItems.toMutableList().also { it.remove(food) }
                    }
                )
            }
        }

        BottomBarFoodSearch(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            nutrientItems = nutrientItems
        )
    }

    showPortionDialog?.let { foodItem ->
        Dialog(onDismissRequest = { showPortionDialog = null }) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                PortionOption(onOptionSelected = { portion ->
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

                    currentFoodItems = currentFoodItems.toMutableList().also { it.add(adjustedFoodItem) }
                    showPortionDialog = null
                })
            }
        }
    }
}

@Preview
@Composable
fun ListFoodnDrinkScreenPreview() {
    val sampleFoodItems = listOf(
        FoodItemData("Nasi Goreng", "625", "200", "8.8", "22.5", "107"),
        FoodItemData("Udang Segar", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Telur Ceplok", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Udang Segar", "625", "50", "8.8", "22.5", "107"),
        FoodItemData("Telur Ceplok", "625", "50", "8.8", "22.5", "107")
    )

    ListFoodnDrinkScreen(
        onBackClick = { /*TODO*/ },
        initialFoodItems = sampleFoodItems,
        onDeleteClick = { /* Handle delete click */ }
    )
}
