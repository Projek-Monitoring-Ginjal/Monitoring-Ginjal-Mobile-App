package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealResultViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

    var dailyNutrientNeedsInfo by mutableStateOf(
        DailyNutrientNeedsInfo(
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        )
    )
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        getDailyNutrientNeedsInfo()
    }

    fun getDailyNutrientNeedsInfo(){
        viewModelScope.launch {
            repository.getLatestDailyNutrientNeedsInfo(DayOptions.FirstDay)
                .handleAsyncDefaultWithUIEvent(_uiEvent){
                    dailyNutrientNeedsInfo = it
                }
        }

    }



}