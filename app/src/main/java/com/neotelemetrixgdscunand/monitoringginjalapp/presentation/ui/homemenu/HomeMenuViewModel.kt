package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.homemenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.githubuserappdicoding.core.domain.common.StaticString
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.handleAsyncDefaultWithUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var job:Job?=null

    var showDialog by mutableStateOf(false)
        private set

    fun onDismissDialog(){
        showDialog = false
    }

    fun onShowDialog(){
        showDialog = true
    }

    fun checkIsInPeriods(){
        job?.cancel()
        job = viewModelScope.launch {
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

    fun logout(){
        job?.cancel()
        job = viewModelScope.launch {
            val isSuccess = repository.logout()
            if(isSuccess){
                _uiEvent.send(
                    UIEvent.UserLogout
                )
            }else{
                _uiEvent.send(
                    UIEvent.ShowToast(
                        StaticString(R.string.expected_behaviour_error_msg)
                    )
                )
            }

        }
    }

}