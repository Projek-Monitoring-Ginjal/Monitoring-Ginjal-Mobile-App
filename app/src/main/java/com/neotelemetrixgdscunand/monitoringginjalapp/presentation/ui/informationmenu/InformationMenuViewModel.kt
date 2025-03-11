package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.informationmenu

import androidx.lifecycle.ViewModel
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class InformationMenuViewModel @Inject constructor():ViewModel() {

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

}