package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Dummy
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.theme.Green20
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.BottomBarFoodSearch
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.PortionOption
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.component.SearchBar
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel.ListFoodnDrinkViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent

@Composable
fun ListFoodnDrinkScreen(
    onBackClick: () -> Unit,
    onNavigateToMealResult: (DayOptions) -> Unit = { },
    viewModel: ListFoodnDrinkViewModel = hiltViewModel()
) {
    val currentFoodItems = viewModel.currentFoodItems
    val nutrientItems = viewModel.nutritionAccumulation
    val dailyNutrientNeedsInfo = viewModel.dailyNutrientNeedsInfo
    val showPortionDialog = viewModel.showPortionDialog
    val isSearching = viewModel.isSearching
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect{
            when(it){
                is UIEvent.ShowToast -> Toast.makeText(
                    context,
                    it.message.getValue(context),
                    Toast.LENGTH_SHORT
                ).show()
                is UIEvent.SuccessUpdateFoodCarts ->{
                    onNavigateToMealResult(
                        viewModel.currentDayOptions
                    )
                }
                else -> {}
            }

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.white))
            .padding(horizontal = 8.dp)
    ) {

        if(viewModel.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Green20
            )
        }else{
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                foodItems = currentFoodItems,
                onAddClick = { foodItem ->
                    viewModel.showPortionDialog(foodItem)
                },
                setListVisibility = viewModel::setListFoodItemsVisibility,
                isListVisible = isSearching,
                searchFoodItems = {
                    viewModel.searchFoodItem(it)
                }
            )

            if(!isSearching){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .weight(1f)
                ) {
                    items(dailyNutrientNeedsInfo.meals) { food ->
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
                    dailyNutrientNeedsThreshold = dailyNutrientNeedsInfo.dailyNutrientNeedsThreshold,
                    nutrition = nutrientItems,
                    onSaveClick = {
                        viewModel.saveDailyNutrientNeedsInfo()
                        onNavigateToMealResult(viewModel.currentDayOptions)
                    },
                )
            }
        }
    }

    showPortionDialog?.let { foodItem ->
        PortionDialog(foodItem = foodItem, viewModel = viewModel, onDismiss = { viewModel.dismissPortionDialog() })
    }
}



@Composable
fun PortionDialog(
    foodItem: FoodItem,
    viewModel: ListFoodnDrinkViewModel,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PortionOption(
                unit = foodItem.unit,
                onOptionSelected = { portion ->
                viewModel.addFoodItem(portion, foodItem)
                onDismiss()
            })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListFoodnDrinkScreenPreview() {
    val sampleFoodItems = remember { Dummy.getFoodItems() }

    ListFoodnDrinkScreen(
        onBackClick = { /*TODO*/ },
        onNavigateToMealResult = { /*TODO*/ }
    )
}
