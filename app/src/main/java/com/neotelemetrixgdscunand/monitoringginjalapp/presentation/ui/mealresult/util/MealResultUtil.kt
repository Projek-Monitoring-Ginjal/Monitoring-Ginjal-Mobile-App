package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.mealresult.util

object MealResultUtil {

    fun calculateProgressFraction(progressAmount:Float, maxAmount:Float):Float{
        val progressFraction = (progressAmount / maxAmount).let {
            if(it > 2f) 2f else it
        }
        return progressFraction
    }

}