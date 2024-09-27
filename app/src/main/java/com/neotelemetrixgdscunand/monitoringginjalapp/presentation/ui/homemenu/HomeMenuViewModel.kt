package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu

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

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun checkIsInPeriods(){
        viewModelScope.launch {
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
        }

    }

}