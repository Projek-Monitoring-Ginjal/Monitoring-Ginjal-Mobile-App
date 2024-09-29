package com.neotelemetrixgdscunand.monitoringginjalapp.di

import com.neotelemetrixgdscunand.monitoringginjalapp.data.local.preference.UserPreferenceImpl
import com.neotelemetrixgdscunand.monitoringginjalapp.data.repository.RepositoryImpl
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.UserPreference
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: RepositoryImpl): Repository

    @Binds
    abstract fun provideUserPreference(userPreference: UserPreferenceImpl): UserPreference

}