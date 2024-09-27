package com.neotelemetrixgdscunand.monitoringginjalapp.data.repository

import com.fajar.githubuserappdicoding.core.domain.common.DynamicString
import com.fajar.githubuserappdicoding.core.domain.common.StaticString
import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neotelemetrixgdscunand.monitoringginjalapp.R
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network.ApiService
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Dummy
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Mapper
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Response
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.UserPreference
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

@Singleton
class RepositoryImpl @Inject constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
    private val gson:Gson
) : Repository {

    private suspend fun <T, S> fetchData(
        fetch: suspend () -> T,
        mapData: suspend T.() -> S,
    ): Resource<S> {
        try {

            val data = fetch.invoke()

            val result = mapData.invoke(data)

            return Resource.Success(
                result
            )
        } catch (e: HttpException) {
            val errorResponseBodyString = e.response()?.errorBody()?.string()

            val responseType = object : TypeToken<Response<String>>(){}.type
            val errorResponse:Response<String> = gson.fromJson(
                errorResponseBodyString,
                responseType
            )
            return Resource.Failure(
                DynamicString(errorResponse.message)
            )
        } catch (e: Exception) {
            println(e.message)

            if (e is CancellationException) throw e

            return Resource.Error(e)
        }
    }


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
        DailyNutrientNeedsInfo(
            dailyNutrientNeedsThreshold = DailyNutrientNeedsThreshold()
        ),
    )

    private val foodItems = Dummy.getFoodItems()

    override suspend fun login(name: String, password: String): Resource<StringRes> {
        return fetchData(
            fetch = {
                apiService.login(
                    name = name,
                    password = password
                )
            },
            mapData = {
                val data = this.data
                userPreference.apply {
                    saveUserId(
                        data?.id ?: throw Exception("There's something wrong..")
                    )
                    saveToken(
                        data.token ?: throw Exception("There's something wrong..")
                    )
                }

                return@fetchData DynamicString(message)
            }
        )
    }

    override suspend fun checkIfAlreadySignedIn(): Boolean {
        val userId = userPreference.getUserId()
        val token = userPreference.getToken()

        return userId != -1 && token.isNotBlank()
    }

    override suspend fun checkIsInNutritionalDailyPeriods(): Resource<Boolean> {
        return fetchData(
            fetch = {
                apiService.checkHemodialisa()
            },
            mapData = {
                val isInNewPeriods = data == null

                return@fetchData !isInNewPeriods
            }
        )
    }

    override suspend fun saveDailyNutrientNeedsThreshold(dailyNutrientNeedsThreshold: DailyNutrientNeedsThreshold): Resource<StringRes> {
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

    override suspend fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions
    ): Resource<DailyNutrientNeedsInfo> {
        /*if(latestDailyNutrientNeedsInfos.isNotEmpty()){
            return Resource.Success(
                latestDailyNutrientNeedsInfos[dayOptions.index]
            )
        }else return Resource.Failure(StaticString(R.string.expected_behaviour_error_msg))*/


    }

    override suspend fun saveDailyNutrientNeedsInfo(dailyNutrientNeedsInfo: DailyNutrientNeedsInfo): Resource<StringRes> {
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

    override suspend fun getAllFoodItems(): Resource<List<FoodItem>> {
        return Resource.Success(foodItems)
    }
}