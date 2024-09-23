package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.dailynutrientscalc.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DailyNutrientCalcUtilVM @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var textState by mutableStateOf("")
        private set
    var showDialog by mutableStateOf(false)
        private set
    var bodyweight by mutableFloatStateOf(0.0f)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onTextChange(newText: String) {
        textState = newText
    }

    fun onSaveClicked() {
        val bw = textState.toFloatOrNull() ?: 0.0f
        if (bw > 0.0f) {
            bodyweight = bw
            showDialog = true
        }
    }

    fun onDismissDialog() {
        showDialog = false
    }

    fun onConfirmDialog(dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold) {
        viewModelScope.launch {
            repository.saveDailyNutrientNeedsThreshold(
                dailyNutrientNeedsThreshold
            ).handleAsyncDefaultWithUIEvent(_uiEvent){
                _uiEvent.send(
                    UIEvent.ShowToast(it)
                )
            }

            showDialog = false
        }
    }
}