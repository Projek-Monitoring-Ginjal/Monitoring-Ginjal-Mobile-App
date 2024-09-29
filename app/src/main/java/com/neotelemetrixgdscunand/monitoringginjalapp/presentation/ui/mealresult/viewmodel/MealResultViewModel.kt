package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util.MealResultUtil
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: Repository
):ViewModel(){

    var dayOptions by
        mutableStateOf(
            savedStateHandle.get<DayOptions>("dayOptions") ?: DayOptions.FirstDay
        )

    private var dailyNutrientNeedsThreshold by mutableStateOf(DailyNutrientNeedsThreshold())

    private var dailyNutrientFourDays by mutableStateOf(
        listOf(
            NutritionEssential(),
            NutritionEssential(),
            NutritionEssential(),
            NutritionEssential(),
        )
    )

    val currentDayMealResult by derivedStateOf {
        MealResultUtil.calculateDailyNutritionAmountAndThreshold(
            dailyNutrientNeedsThreshold,
            dailyNutrientFourDays,
            dayOptions
        )
    }


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var job: Job? = null

    init {
        getMealResultsInfo()
    }

    private fun getMealResultsInfo(){
        job?.cancel()
        job = viewModelScope.launch {
            repository.getHemodialisaResults().handleAsyncDefaultWithUIEvent(
                _uiEvent,
            ){
                val (dailyThresholds, listNutritionDays) = it
                dailyNutrientNeedsThreshold = dailyThresholds
                dailyNutrientFourDays = listNutritionDays
            }
        }
    }
}