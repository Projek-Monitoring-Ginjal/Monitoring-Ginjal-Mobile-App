package com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.neotelemetrixgdscunand.monitoringginjalapp.model.FoodItemData
import com.neotelemetrixgdscunand.monitoringginjalapp.model.getFoodItems
import com.neotelemetrixgdscunand.monitoringginjalapp.model.getNutrientItems
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.BottomBarFoodSearch
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.PortionOption
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.SearchBar


@SuppressLint("MutableCollectionMutableState")
@Composable
fun ListFoodnDrinkScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    initialFoodItems: List<FoodItemData>,
    onDeleteClick: (FoodItemData) -> Unit = { },
    onNavigateToMealResult: () -> Unit = { },
) {
    var currentFoodItems by remember { mutableStateOf(initialFoodItems.toMutableList()) }
    var showPortionDialog by remember { mutableStateOf<FoodItemData?>(null) }

    val nutrientItems = remember {
        getNutrientItems(currentFoodItems)
    }


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Toolbar(onBackClick = onBackClick)

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
            nutrientItems = nutrientItems,
            onSaveClick = onNavigateToMealResult
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

@Preview(showBackground = true)
@Composable
fun ListFoodnDrinkScreenPreview() {

    val sampleFoodItems = remember {
        getFoodItems()
    }

    ListFoodnDrinkScreen(
        onBackClick = { /*TODO*/ },
        initialFoodItems = sampleFoodItems,
        onDeleteClick = { /* Handle delete click */ }
    )
}
