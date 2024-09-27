package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network

import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.CheckHemodialisaResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.response.LoginResponse
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.common.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("name") name:String,
        @Field("password") password:String
    ): Response<LoginResponse>

    @POST("hemodialisa/check")
    suspend fun checkHemodialisa():Response<CheckHemodialisaResponse>

    @POST("hemodialisa")
    @FormUrlEncoded
    suspend fun startNewHemodialisaPeriods(
        @Field("body_weight")
        bodyweight:Float
    ):Response<>

}