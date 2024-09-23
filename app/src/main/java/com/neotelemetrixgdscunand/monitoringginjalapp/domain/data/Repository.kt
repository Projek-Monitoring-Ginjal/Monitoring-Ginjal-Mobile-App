package com.neotelemetrixgdscunand.monitoringginjalapp.domain.data

import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem

interface Repository {

    fun login(name:String, password:String):Resource<StringRes>
    fun checkIsInNutritionalDailyPeriods():Resource<Boolean>
    fun saveDailyNutrientNeedsThreshold(
        dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold
    ):Resource<StringRes>
    fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ):Resource<DailyNutrientNeedsInfo>
    fun getAllFoodItems():Resource<List<FoodItem>>

    fun saveDailyNutrientNeedsInfo(
        dailyNutrientNeedsInfo: DailyNutrientNeedsInfo
    ):Resource<StringRes>

}