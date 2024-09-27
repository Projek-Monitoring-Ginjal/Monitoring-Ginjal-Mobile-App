package com.neotelemetrixgdscunand.monitoringginjalapp.domain.data

import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem

interface Repository {

    suspend fun login(name:String, password:String):Resource<StringRes>
    suspend fun checkIsInNutritionalDailyPeriods():Resource<Boolean>
    suspend fun saveDailyNutrientNeedsThreshold(
        dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold
    ):Resource<StringRes>
    suspend fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ):Resource<DailyNutrientNeedsInfo>
    suspend fun getAllFoodItems():Resource<List<FoodItem>>

    suspend fun checkIfAlreadySignedIn():Boolean

    suspend fun saveDailyNutrientNeedsInfo(
        dailyNutrientNeedsInfo: DailyNutrientNeedsInfo
    ):Resource<StringRes>


}