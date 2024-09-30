package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class LoginViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    var isSignedIn:Boolean? by mutableStateOf(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var name by mutableStateOf("")
    var password by mutableStateOf("")
    var language by mutableStateOf("")

    private var job: Job?= null

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        checkIfAlreadySignedIn()
    }

    fun login(languageCode:String){
        job?.cancel()
        job = viewModelScope.launch {
            isLoading = true
            repository.login(
                name, password, languageCode
            ).handleAsyncDefaultWithUIEvent(
                _uiEvent,
                onSuccess = {
                    _uiEvent.send(
                        UIEvent.ShowToast(
                            it
                        )
                    )
                    isSignedIn = true
                }
            )

        }.also {
            it.invokeOnCompletion {
                isLoading = false
            }
        }
    }

    private fun checkIfAlreadySignedIn(){
        viewModelScope.launch {
            val isAlreadySignedIn = repository.checkIfAlreadySignedIn()
            isSignedIn = isAlreadySignedIn
        }

    }

}