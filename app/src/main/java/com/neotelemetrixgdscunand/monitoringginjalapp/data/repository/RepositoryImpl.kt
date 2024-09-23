package com.neotelemetrixgdscunand.monitoringginjalapp.data.repository

import com.fajar.githubuserappdicoding.core.domain.common.StaticString
import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Dummy
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Mapper
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor() : Repository {

    private var latestDailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold? = null
    private var latestDailyNutrientNeedsInfos:MutableList<DailyNutrientNeedsInfo> = mutableListOf(
        DailyNutrientNeedsInfo(
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        ),
        DailyNutrientNeedsInfo(
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        ),
        DailyNutrientNeedsInfo(
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        ),
    )

    private val foodItems = Dummy.getFoodItems()

    override fun login(name: String, password: String): Resource<StringRes> {
        return Resource.Success(
            StaticString(
                R.string.login_success
            )
        )
    }

    override fun checkIsInNutritionalDailyPeriods(): Resource<Boolean> {
        return Resource.Success(false)
    }

    override fun saveDailyNutrientNeedsThreshold(dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold): Resource<StringRes> {
        latestDailyNutrientNeedsThreshold = dailyNutrientNeedsThreshold

        latestDailyNutrientNeedsInfos.forEachIndexed{ index, it ->
            val updatedDailyNutrientNeedsInfo = Mapper.setDailyNutritionThresholdToDailyNutrientNeedsInfo(
                it, dailyNutrientNeedsThreshold
            )
            latestDailyNutrientNeedsInfos[index] = updatedDailyNutrientNeedsInfo
        }

        return Resource.Success(
            StaticString(
                R.string.daily_nutrient_needs_threshold_saved_successfully
            )
        )
    }

    override fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ): Resource<DailyNutrientNeedsInfo> {
        if(latestDailyNutrientNeedsInfos.isNotEmpty()){
            return Resource.Success(
                latestDailyNutrientNeedsInfos[dayOptions.index]
            )
        }else return Resource.Failure(StaticString(R.string.expected_behaviour_error_msg))
    }

    override fun saveDailyNutrientNeedsInfo(dailyNutrientNeedsInfo: DailyNutrientNeedsInfo): Resource<StringRes> {
        val index = latestDailyNutrientNeedsInfos.indexOfFirst {
            it.id == dailyNutrientNeedsInfo.id
        }
        latestDailyNutrientNeedsInfos[index] = dailyNutrientNeedsInfo

        return Resource.Success(
            StaticString(
                R.string.daily_nutrient_needs_information_saved_successfully
            )
        )
    }

    override fun getAllFoodItems(): Resource<List<FoodItem>> {
        return Resource.Success(foodItems)
    }
}