package com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.model.FoodItemData
import com.neotelemetrixgdscunand.monitoringginjalapp.model.getFoodItems
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.BottomBarFoodSearch
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.PortionOption
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.component.SearchBar
import com.neotelemetrixgdscunand.monitoringginjalapp.ui.listfoodndrink.viewmodel.ListFoodnDrinkViewModel

@Composable
fun ListFoodnDrinkScreen(
    onBackClick: () -> Unit,
    initialFoodItems: List<FoodItemData>,
    onNavigateToMealResult: () -> Unit = { },
    viewModel: ListFoodnDrinkViewModel = viewModel()
) {
    val currentFoodItems by viewModel.currentFoodItems
    val nutrientItems by viewModel.nutrientItems
    val showPortionDialog by viewModel.showPortionDialog

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
            .padding(horizontal = 8.dp)
    ) {

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            searchFoodItems = initialFoodItems,
            onAddClick = { foodItem ->
                viewModel.showPortionDialog(foodItem)
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .weight(1f)
        ) {
            items(currentFoodItems) { food ->
                FoodItem(
                    food = food,
                    onDeleteClick = {
                        viewModel.deleteFoodItem(food)
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
        PortionDialog(foodItem = foodItem, viewModel = viewModel, onDismiss = { viewModel.dismissPortionDialog() })
    }
}



@Composable
fun PortionDialog(
    foodItem: FoodItemData,
    viewModel: ListFoodnDrinkViewModel,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PortionOption(onOptionSelected = { portion ->
                viewModel.updateFoodItem(portion, foodItem)
                onDismiss()
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListFoodnDrinkScreenPreview() {
    val sampleFoodItems = remember { getFoodItems() }

    ListFoodnDrinkScreen(
        onBackClick = { /*TODO*/ },
        initialFoodItems = sampleFoodItems,
        onNavigateToMealResult = { /*TODO*/ }
    )
}
