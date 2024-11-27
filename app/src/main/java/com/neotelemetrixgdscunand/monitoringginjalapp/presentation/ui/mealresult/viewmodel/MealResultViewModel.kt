package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.githubuserappdicoding.core.domain.common.StaticString
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
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
    val hemodialisaType = savedStateHandle.get<HemodialisaType>("hemodialisaType") ?: HemodialisaType.HEMODIALISA_1

    var urineInputText:String by mutableStateOf("")
        private set

    fun onUrineInputTextChange(newText:String){
        urineInputText = newText
    }

    var dailyNutrientNeedsThresholdFourDays by mutableStateOf(
        List<DailyNutrientNeedsThreshold?>(dayOptions.index + 1){
            DailyNutrientNeedsThreshold()
        }
    )
        private set

    var dailyNutrientFourDays:List<NutritionEssential?> by mutableStateOf(
        listOf(
            NutritionEssential(),
            NutritionEssential(),
            NutritionEssential(),
            NutritionEssential(),
        )
    )
        private set

    var isDialogInputUrineConfirmationIsShown by mutableStateOf(false)
        private set

    var nutrientNeedsThresholdDialogContent:NutritionEssential? by mutableStateOf(
        null
    )
        private set

    fun onDialogInputUrineConfirmationShown(){
        isDialogInputUrineConfirmationIsShown = true
    }

    fun onDialogInputUrineConfirmationDismiss(){
        isDialogInputUrineConfirmationIsShown = false
    }

    fun onDialogNutrientNeedsThresholdDismiss(){
        nutrientNeedsThresholdDialogContent = null
    }

    val tabsTextResId:List<Int> =
        mutableListOf(
            R.string.hari_1,
            R.string.hari_2,
            R.string.hari_3,
        ).also {
            if(hemodialisaType == HemodialisaType.HEMODIALISA_2){
                it.add(R.string.hari_4)
            }
        }



    var isLoading by mutableStateOf(false)
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getMealResultJob: Job? = null
    private var inputUrineJob:Job? = null

    init {
        getMealResultsInfo()
    }

    var selectedTabIndex by mutableIntStateOf(dayOptions.index)
        private set


    fun changeTab(newTabIndex:Int){
        if(selectedTabIndex == newTabIndex) return

        urineInputText = ""
        dayOptions = DayOptions.entries[newTabIndex]
        selectedTabIndex = newTabIndex
    }

    private fun getMealResultsInfo(){
        getMealResultJob?.cancel()
        getMealResultJob = viewModelScope.launch {
            isLoading = true
            repository.getHemodialisaResults().handleAsyncDefaultWithUIEvent(
                _uiEvent,
            ){
                val (listDailyThreshold, listNutritionDays) = it
                dailyNutrientNeedsThresholdFourDays = listDailyThreshold
                dailyNutrientFourDays = listNutritionDays
            }
        }.also {
            it.invokeOnCompletion {
                isLoading = false
            }
        }
    }

    fun inputUrine(){
        onDialogInputUrineConfirmationDismiss()

        if(urineInputText.isBlank()){
            viewModelScope.launch {
                _uiEvent.send(
                    UIEvent.ShowToast(
                        StaticString(
                            R.string.jumlah_urine_yang_dimasukkan_tidak_valid
                        )
                    )
                )
            }
            return
        }

        val commaIndex = urineInputText.indexOfFirst { it == ',' }
        val adjustedText = if(commaIndex != -1){
            val checkedText = if(commaIndex == urineInputText.lastIndex){
                urineInputText = urineInputText.substring(0) + "0"
                urineInputText
            }else urineInputText
            """
                ${checkedText.substring(0, commaIndex)}.${checkedText.substring(commaIndex+1)}
            """.trimIndent()
        }else urineInputText

        val urineAmount = adjustedText.toFloatOrNull() ?: 0.0f

        inputUrineJob?.cancel()
        inputUrineJob = viewModelScope.launch {
            isLoading = true

            repository.inputUrine(
                dayOptions,
                urineAmount = urineAmount
            ).handleAsyncDefaultWithUIEvent(_uiEvent){
                val (nutritionNeeds, message) = it

                _uiEvent.send(
                    UIEvent.ShowToast(message)
                )

                nutrientNeedsThresholdDialogContent = nutritionNeeds

                getMealResultsInfo()
            }
        }.also{
            it.invokeOnCompletion {
                isLoading = false
            }
        }
    }
}