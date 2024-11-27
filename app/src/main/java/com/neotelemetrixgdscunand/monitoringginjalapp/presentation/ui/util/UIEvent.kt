package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util

import com.fajar.githubuserappdicoding.core.domain.common.Event
import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType

sealed class UIEvent:Event() {
    data class ShowToast(val message:StringRes): UIEvent()

    data class NavigateToExistingHemodialisaPeriod(val hemodialisaType: HemodialisaType) : UIEvent()

    data object SuccessUpdateFoodCarts:UIEvent()

    data object UserLogout:UIEvent()

}