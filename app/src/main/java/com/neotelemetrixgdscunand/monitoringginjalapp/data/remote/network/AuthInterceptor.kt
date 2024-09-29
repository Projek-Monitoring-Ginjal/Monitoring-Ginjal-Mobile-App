package com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network

import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.UserPreference
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val userPreference: UserPreference
):Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        var token:String
        return runBlocking {
            token = userPreference.getToken()
            if(token.isNotEmpty()){
                val authorized = original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                return@runBlocking chain.proceed(authorized)
            }
            chain.proceed(original)
        }

    }
}