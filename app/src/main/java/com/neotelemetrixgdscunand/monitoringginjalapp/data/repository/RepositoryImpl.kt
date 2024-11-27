package com.neotelemetrixgdscunand.monitoringginjalapp.data.repository

import com.fajar.githubuserappdicoding.core.domain.common.DynamicString
import com.fajar.githubuserappdicoding.core.domain.common.StringRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network.ApiService
import com.neotelemetrixgdscunand.monitoringginjalapp.data.repository.Mapper.mapToDailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.data.repository.Mapper.mapToFoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.data.repository.Mapper.mapToFoodItemBody
import com.neotelemetrixgdscunand.monitoringginjalapp.data.repository.Mapper.mapToMealResultInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Resource
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Response
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.UserPreference
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsInfo
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DailyNutrientNeedsThreshold
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.DayOptions
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodItem
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.FoodNutrient
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.HemodialisaType
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.model.NutritionEssential
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

            if (e is CancellationException) throw e

            return Resource.Error(e)
        }
    }



    //private val foodItems = Dummy.getFoodItems()

    override suspend fun login(name: String, password: String, languageCode:String): Resource<StringRes> {
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
                    saveLanguageCode(languageCode)
                }

                return@fetchData DynamicString(message)
            }
        )
    }

    override suspend fun checkIfAlreadySignedIn(): Boolean {
        val userId = userPreference.getUserId()
        val token = userPreference.getToken()
        val languageCode = userPreference.getLanguageCode()

        return userId != -1 && token.isNotBlank() && languageCode.isNotBlank()
    }

    override suspend fun checkIsInNutritionalDailyPeriods(): Resource<Pair<Boolean, HemodialisaType?>> {
        return fetchData(
            fetch = {
                apiService.checkHemodialisa()
            },
            mapData = {
                val isInNewPeriods = data == null
                val hemodialisaType:HemodialisaType?= data?.hemodialisaType?.let {
                    HemodialisaType.getHemodialisaType(it)
                }

                return@fetchData Pair(!isInNewPeriods, hemodialisaType)
            }
        )
    }

    override suspend fun startNewHemodialisa(
        bodyWeight:Float,
        hemodialisaType: HemodialisaType
    ): Resource<Pair<NutritionEssential?, StringRes>> {
        return fetchData(
            fetch = {
                apiService.startNewHemodialisaPeriods(
                    bodyWeight,
                    hemodialisaType.value
                )
            },
            mapData = {
                val nutritionNeeds = this.data?.let {
                    NutritionEssential(
                        calorie = FoodNutrient.Calorie(it.calories ?: throw Exception("error")),
                        protein = FoodNutrient.Protein(it.protein ?: throw Exception("error")),
                        fluid = FoodNutrient.Fluid(it.fluids ?: throw Exception("error")),
                        sodium = FoodNutrient.Sodium(it.sodium ?: throw Exception("error")),
                        potassium = FoodNutrient.Potassium(it.potassium ?: throw  Exception("error"))
                    )
                }
                Pair(nutritionNeeds, DynamicString(message))
            }
        )
    }

    override suspend fun inputUrine(
        dayOptions: DayOptions,
        urineAmount: Float
    ): Resource<Pair<NutritionEssential,StringRes>> {
        val day = dayOptions.index + 1
        return fetchData(
            fetch = {
                apiService.inputUrine(day, urineAmount)
            },
            mapData = {
                val isSuccess = this.data != null
                if(!isSuccess){
                    throw Exception(this.message)
                }

                val nutritionNeeds = this.data!!.let {
                    NutritionEssential(
                        calorie = FoodNutrient.Calorie(it.calories ?: throw Exception("error")),
                        protein = FoodNutrient.Protein(it.protein ?: throw Exception("error")),
                        fluid = FoodNutrient.Fluid(it.fluids ?: throw Exception("error")),
                        sodium = FoodNutrient.Sodium(it.sodium ?: throw Exception("error")),
                        potassium = FoodNutrient.Potassium(it.potassium ?: throw  Exception("error"))
                    )
                }

                Pair(nutritionNeeds, DynamicString(this.message))
            }
        )
    }

    override suspend fun getLatestDailyNutrientNeedsInfo(
        dayOptions: DayOptions,
    ): Resource<DailyNutrientNeedsInfo> {
        return fetchData(
            fetch = {
                val languageCode = userPreference.getLanguageCode()
                apiService.getFoodCarts(
                    dayOptions.index + 1,
                    languageCode
                )
            },
            mapData = {
                val dailyNutrientNeedsInfo = this.data?.mapToDailyNutrientNeedsInfo(dayOptions) ?: throw Exception("error...")
                dailyNutrientNeedsInfo
            }
        )
    }

    override suspend fun saveDailyNutrientNeedsInfo(dailyNutrientNeedsInfo: DailyNutrientNeedsInfo): Resource<StringRes> {
        val foodItemsBody = dailyNutrientNeedsInfo.meals.map {
            it.mapToFoodItemBody()
        }
        val jsonifyStringBody = "[${foodItemsBody.joinToString(",")}]"
        return fetchData(
            fetch = {

                apiService.updateFoodCarts(
                    dailyNutrientNeedsInfo.day.index + 1,
                    jsonifyStringBody
                )
            },
            mapData = {
                return@fetchData DynamicString(message)
            }
        )
    }

    override suspend fun searchFoodItems(query:String): Resource<List<FoodItem>> {
        return fetchData(
            fetch = {
                val langCode = userPreference.getLanguageCode()
                apiService.searchItem(langCode, query)
            },
            mapData = {
                val foodItems = this.data?.map {
                    it.mapToFoodItem()
                } ?: emptyList()

                return@fetchData foodItems
            }
        )
    }

    override suspend fun getHemodialisaResults(): Resource<Pair<List<DailyNutrientNeedsThreshold?>, List<NutritionEssential?>>> {
        return fetchData(
            fetch = {
                apiService.getHemodialisaResult()
            },
            mapData = {
                val hemodialisaType = HemodialisaType.getHemodialisaType(this.data?.hemodialisaType ?: 1)

                val pairs = this.data?.mapToMealResultInfo(hemodialisaType) ?: throw Exception("error")
                pairs
            }
        )
    }

    override suspend fun logout(): Boolean {
        userPreference.apply {
            deleteToken()
            deleteUserId()
            deleteLanguageCode()
        }

        val userId = userPreference.getUserId()
        val token = userPreference.getToken()
        val languageCode = userPreference.getLanguageCode()

        return userId == -1 && token.isBlank() && languageCode == ""
    }
}

