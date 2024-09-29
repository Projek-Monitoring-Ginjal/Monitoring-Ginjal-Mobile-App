package com.neotelemetrixgdscunand.monitoringginjalapp.domain.data

import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential

interface Repository {

    suspend fun login(name:String, password:String, languageCode:String):Resource<StringRes>

    suspend fun checkIsInNutritionalDailyPeriods():Resource<Boolean>

    suspend fun startNewHemodialisa(
        bodyWeight:Float
    ):Resource<Pair<NutritionEssential?, StringRes>>

    suspend fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ):Resource<DailyNutrientNeedsInfo>
    suspend fun searchFoodItems(query:String):Resource<List<FoodItem>>

    suspend fun checkIfAlreadySignedIn():Boolean

    suspend fun saveDailyNutrientNeedsInfo(
        dailyNutrientNeedsInfo: DailyNutrientNeedsInfo
    ):Resource<StringRes>

    suspend fun getHemodialisaResults():Resource<Pair<DailyNutrientNeedsThreshold, List<NutritionEssential>>>

}