package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network

import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.CheckHemodialisaResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.FoodContentItemResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.GetFoodCartResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.GetHemodialisaResultResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.LoginResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("name") name:String,
        @Field("password") password:String
    ): Response<LoginResponse>

    @POST("hemodialisa/check")
    suspend fun checkHemodialisa():Response<CheckHemodialisaResponse>

    /*@POST("hemodialisa")
    @FormUrlEncoded
    suspend fun startNewHemodialisaPeriods(
        @Field("body_weight")
        bodyweight:Float
    ):Response<>*/

    @GET("makanan-search")
    suspend fun searchItem(
        @Query("lang")
        langCode: String,
        @Query("query")
        query:String
    ):Response<List<FoodContentItemResponse>>

    @GET("makananByDay/{day}/{lang_code}")
    suspend fun getFoodCarts(
        @Path("day")
        day:Int,
        @Path("lang_code")
        langCode:String
    ):Response<GetFoodCartResponse>

    @FormUrlEncoded
    @POST("makanan/update/{day}")
    suspend fun updateFoodCarts(
        @Path("day")
        day:Int,
        @Field("listMakan")
        meals:String
    ):Response<String?>

    @POST("hemodialisa/results")
    suspend fun getHemodialisaResult():Response<GetHemodialisaResultResponse>

}