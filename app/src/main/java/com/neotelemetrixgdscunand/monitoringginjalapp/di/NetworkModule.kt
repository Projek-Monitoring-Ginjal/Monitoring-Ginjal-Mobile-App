package com.neotelemetrixgdscunand.monitoringginjalapp.di

import com.google.gson.Gson
import com.neotelemetrixgdscunand.monitoringginjalapp.BuildConfig
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network.ApiService
import com.neotelemetrixgdscunand.monitoringginjalapp.data.remote.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(
        okHttpClient: OkHttpClient
    ):ApiService{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideGson() = Gson()

}