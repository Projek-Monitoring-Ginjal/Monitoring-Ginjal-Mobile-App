package com.neotelemetrixgdscunand.monitoringginjalapp.di

import com.neotelemetrixgdscunand.monitoringginjalapp.data.repository.RepositoryImpl
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(repository: RepositoryImpl): Repository

}