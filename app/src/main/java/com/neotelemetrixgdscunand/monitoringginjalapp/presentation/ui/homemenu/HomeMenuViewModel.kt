package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeMenuViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun checkIsInPeriods(){
        viewModelScope.launch {
            isLoading = true
            repository.checkIsInNutritionalDailyPeriods()
                .handleAsyncDefaultWithUIEvent(
                    _uiEvent,
                    onSuccess = { isInPeriods ->
                        _uiEvent.send(
                            UIEvent.DetermineNextScreenHemodialisaCheck(
                                isInPeriods = isInPeriods
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

}