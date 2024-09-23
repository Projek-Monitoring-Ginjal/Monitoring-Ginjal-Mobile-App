package com.neotelemetrixgdscunand.monitoringginjalapp.domain.common

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold

object Mapper {

    fun setDailyNutritionThresholdToDailyNutrientNeedsInfo(
        dailyNutrientNeedsInfo: DailyNutrientNeedsInfo,
        dailyNutritionThreshold: DailyNutrientNeedsThreshold
    ):DailyNutrientNeedsInfo{
        return dailyNutrientNeedsInfo.copy(
            dailyNutrientNeedsThreshold = dailyNutritionThreshold
        )
    }

}