package com.neotelemetrixgdscunand.monitoringginjalapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.util.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {
    @Provides
    fun provideDataStorePrefs(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.dataStore
    }

}