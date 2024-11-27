package com.neotelemetrixgdscunand.monitoringginjalapp.domain.data

import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential

interface Repository {

    suspend fun login(name:String, password:String, languageCode:String):Resource<StringRes>

    suspend fun checkIsInNutritionalDailyPeriods():Resource<Pair<Boolean, HemodialisaType?>>

    suspend fun startNewHemodialisa(
        bodyWeight:Float,
        hemodialisaType: HemodialisaType
    ):Resource<Pair<NutritionEssential?, StringRes>>

    suspend fun inputUrine(
        dayOptions:DayOptions,
        urineAmount:Float
    ):Resource<Pair<NutritionEssential?, StringRes>>

    suspend fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ):Resource<DailyNutrientNeedsInfo>
    suspend fun searchFoodItems(query:String):Resource<List<FoodItem>>

    suspend fun checkIfAlreadySignedIn():Boolean

    suspend fun saveDailyNutrientNeedsInfo(
        dailyNutrientNeedsInfo: DailyNutrientNeedsInfo
    ):Resource<StringRes>

    suspend fun getHemodialisaResults():Resource<Pair<List<DailyNutrientNeedsThreshold?>, List<NutritionEssential?>>>

    suspend fun logout():Boolean

}