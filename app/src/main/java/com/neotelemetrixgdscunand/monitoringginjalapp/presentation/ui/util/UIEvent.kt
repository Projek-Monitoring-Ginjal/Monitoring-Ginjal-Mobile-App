package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util

import com.fajar.githubuserappdicoding.core.domain.common.Event
import com.fajar.githubuserappdicoding.core.domain.common.StringRes

sealed class UIEvent:Event() {
    data class ShowToast(val message:StringRes): UIEvent()

    data class DetermineNextScreenHemodialisaCheck(
        val isInPeriods:Boolean
    ) : UIEvent()

    data object SuccessUpdateFoodCarts:UIEvent()

    data object UserLogout:UIEvent()

}