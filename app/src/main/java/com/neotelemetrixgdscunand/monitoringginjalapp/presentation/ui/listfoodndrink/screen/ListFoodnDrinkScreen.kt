package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemData
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.getFoodItems
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.BottomBarFoodSearch
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.PortionOption
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.SearchBar
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel.ListFoodnDrinkViewModel

@Composable
fun ListFoodnDrinkScreen(
    onBackClick: () -> Unit,
    initialFoodItems: List<FoodItemData>,
    onNavigateToMealResult: () -> Unit = { },
    viewModel: ListFoodnDrinkViewModel = hiltViewModel()
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
