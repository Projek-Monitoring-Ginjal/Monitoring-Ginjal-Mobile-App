package com.neotelemetrixgdscunand.monitoringginjalapp.ui.bodyweightinput.viewmodel

import androidx.lifecycle.ViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.model.NutrientThresholds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class PengaturanMakanViewModel : ViewModel() {

    private val _textState = MutableStateFlow("")
    val textState: StateFlow<String> = _textState

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _bbk = MutableStateFlow(0.0)
    val bbk: StateFlow<Double> = _bbk

    fun onTextChange(newText: String) {
        _textState.value = newText
    }

    fun onSaveClicked() {
        val bbkValue = _textState.value.toDoubleOrNull() ?: 0.0
        if (bbkValue > 0) {
            _bbk.value = bbkValue
            _showDialog.value = true
        }
    }

    fun onDismissDialog() {
        _showDialog.value = false
    }

    fun onConfirmDialog() {
        val bbkValue = _bbk.value
        NutrientThresholds.updateThresholds(
            calories = (bbkValue * 35),
            liquid = (bbkValue * 0.8 + 500),
            protein = (bbkValue * 1.2),
            sodium = 200.0,
            potassium = 2500.0
        )
        _showDialog.value = false
    }
}
