package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodPortionOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.util.ListFoodnDrinkUtil
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.changePortion
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFoodnDrinkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
) : ViewModel() {

    val currentDayOptions = savedStateHandle.get<DayOptions>("dayOptions") ?: DayOptions.FirstDay

    var currentFoodItems by mutableStateOf<List<FoodItem>>(emptyList())
        private set

    var dailyNutrientNeedsInfo by mutableStateOf(
        DailyNutrientNeedsInfo(
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        )
    )

    var nutrientItems by mutableStateOf(ListFoodnDrinkUtil.sumFoodNutritions(dailyNutrientNeedsInfo.meals))
        private set

    var showPortionDialog by mutableStateOf<FoodItem?>(null)
        private set

    var isSearching by mutableStateOf(false)
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getDailyNutrientNeedsInfo()
        getFoodItems()
    }

    private fun getDailyNutrientNeedsInfo() {
        viewModelScope.launch {
            repository.getLatestDailyNutrientNeedsInfo(currentDayOptions)
                .handleAsyncDefaultWithUIEvent(_uiEvent){
                    dailyNutrientNeedsInfo = it
                }
        }
    }

    private fun getFoodItems() {
        viewModelScope.launch {
            repository.getAllFoodItems()
                .handleAsyncDefaultWithUIEvent(_uiEvent){
                    currentFoodItems = it
                }
        }
    }

    fun saveDailyNutrientNeedsInfo() {
        viewModelScope.launch {
            repository.saveDailyNutrientNeedsInfo(dailyNutrientNeedsInfo)
                .handleAsyncDefaultWithUIEvent(_uiEvent){
                    _uiEvent.send(UIEvent.ShowToast(it))
                }
        }
    }

    fun deleteFoodItem(foodItem: FoodItem) {
        dailyNutrientNeedsInfo = dailyNutrientNeedsInfo.copy(
            meals = dailyNutrientNeedsInfo.meals
                .toMutableList()
                .also {
                    it.remove(foodItem)
                }
        )
        updateDailyNutrientInfo()
    }


    fun showPortionDialog(foodItem: FoodItem) {
        showPortionDialog = foodItem
    }

    fun dismissPortionDialog() {
        showPortionDialog = null
    }

    fun addFoodItem(portion: FoodPortionOptions, foodItem: FoodItem) {

        val adjustedFoodItem = foodItem.changePortion(portion)

        dailyNutrientNeedsInfo = dailyNutrientNeedsInfo.copy(
            meals = dailyNutrientNeedsInfo.meals
                .toMutableList()
                .also {
                    it.add(adjustedFoodItem)
                }
        )

        updateDailyNutrientInfo()
    }

    private fun updateDailyNutrientInfo() {
        nutrientItems = ListFoodnDrinkUtil.sumFoodNutritions(dailyNutrientNeedsInfo.meals)
    }

    fun setListFoodItemsVisibility(isVisible:Boolean){
        isSearching = isVisible
    }

}
