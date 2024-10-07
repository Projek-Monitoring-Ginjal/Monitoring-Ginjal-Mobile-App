package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.viewmodel


import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.githubuserappdicoding.core.domain.common.DynamicString
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItemCart
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodPortionOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.listfoodndrink.util.ListFoodnDrinkUtil
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.changePortion
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var oldDailyNutrientThreshold by mutableStateOf(
        DailyNutrientNeedsThreshold()
    )

    var isLoading by mutableStateOf(false)
        private set

    var dailyNutrientNeedsInfo by mutableStateOf(
        DailyNutrientNeedsInfo(
            DayOptions.FirstDay,
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        )
    )
        private set

    private var job:Job? = null

    val nutritionAccumulation by derivedStateOf {
        ListFoodnDrinkUtil.sumFoodNutritions(
            dailyNutrientNeedsInfo.meals,
            currentDayOptions,
            nutritionInfoFourDays,
            oldDailyNutrientThreshold ?: throw Exception("error")
        )
    }

    var showPortionDialog by mutableStateOf<FoodItem?>(null)
        private set

    var isSearching by mutableStateOf(false)
        private set

    private var searchJob: Job? = null

    private var nutritionInfoFourDays = listOf(
        NutritionEssential(),
        NutritionEssential(),
        NutritionEssential(),
        NutritionEssential(),
    )

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getDailyNutrientNeedsInfo()
        getFoodItems()
    }

    private fun getDailyNutrientNeedsInfo() {
        job?.cancel()
        job = viewModelScope.launch {
            isLoading = true
            var dailyNeedsInfo:DailyNutrientNeedsInfo? = null

            repository.getLatestDailyNutrientNeedsInfo(currentDayOptions)
                .handleAsyncDefaultWithUIEvent(_uiEvent) {
                    dailyNeedsInfo = it
                }

            if(dailyNeedsInfo == null) {
                _uiEvent.send(
                    UIEvent.ShowToast(DynamicString("error.."))
                )
                return@launch
            }

            repository.getHemodialisaResults()
                .handleAsyncDefaultWithUIEvent(
                    _uiEvent
                ){
                    val (_, listNutritions) = it
                    nutritionInfoFourDays = listNutritions
                    oldDailyNutrientThreshold = dailyNeedsInfo?.dailyNutrientNeedsThreshold ?: throw Exception()
                   val adjustedDailyNutritionInfo =  dailyNeedsInfo?: throw Exception("error")
                    dailyNutrientNeedsInfo = adjustedDailyNutritionInfo
                }
        }.also {
            it.invokeOnCompletion {
                isLoading = false
            }
        }
    }

    private fun getFoodItems() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repository.searchFoodItems("")
                .handleAsyncDefaultWithUIEvent(_uiEvent) {
                    currentFoodItems = it
                }
        }
    }

    fun saveDailyNutrientNeedsInfo() {
        job?.cancel()
        job = viewModelScope.launch {
            isLoading = true
            repository.saveDailyNutrientNeedsInfo(dailyNutrientNeedsInfo)
                .handleAsync(
                    onSuccess = {
                        _uiEvent.send(
                            UIEvent.ShowToast(
                                it
                            )
                        )
                        _uiEvent.send(
                            UIEvent.SuccessUpdateFoodCarts
                        )

                    },
                    onFailure = {
                        _uiEvent.send(
                            UIEvent.ShowToast(
                                it
                            )
                        )
                    },
                    onError = {
                        _uiEvent.send(
                            UIEvent.ShowToast(
                                DynamicString(
                                    it.message.toString()
                                )
                            )
                        )
                    }
                )
        }.also {
            it.invokeOnCompletion {
                isLoading = false
            }
        }
    }

    fun deleteFoodItem(foodItemCart: FoodItemCart) {
        dailyNutrientNeedsInfo = dailyNutrientNeedsInfo.copy(
            meals = dailyNutrientNeedsInfo.meals
                .toMutableList()
                .also {
                    it.remove(foodItemCart)
                }
        )
    }


    fun showPortionDialog(foodItem: FoodItem) {
        showPortionDialog = foodItem
    }

    fun dismissPortionDialog() {
        showPortionDialog = null
    }

    fun addFoodItem(portion: FoodPortionOptions, foodItem: FoodItem) {
        val adjustedFoodItemCart = foodItem.changePortion(portion)

        dailyNutrientNeedsInfo = dailyNutrientNeedsInfo.copy(
            meals = dailyNutrientNeedsInfo.meals
                .toMutableList()
                .also {
                    it.add(adjustedFoodItemCart)
                }
        )
    }


    fun searchFoodItem(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            repository.searchFoodItems(query)
                .handleAsyncDefaultWithUIEvent(_uiEvent) { foodItems ->
                    currentFoodItems = foodItems
                }
        }
    }

    fun setListFoodItemsVisibility(isVisible: Boolean) {
        isSearching = isVisible
    }

}
