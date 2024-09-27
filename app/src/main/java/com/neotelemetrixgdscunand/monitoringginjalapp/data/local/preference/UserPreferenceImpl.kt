package com.neotelemetrixgdscunand.monitoringginjalapp.data.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.neotelemetrixgdscunand.monitoringginjalapp.domain.data.UserPreference
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserPreferenceImpl @Inject constructor(
    private val dataStorePrefs : DataStore<Preferences>
):UserPreference {

    override suspend fun getToken(): String {
        val prefs = dataStorePrefs.data.first()
        return prefs[TOKEN] ?: ""
    }

    override suspend fun getUserId(): Int {
        val prefs = dataStorePrefs.data.first()
        return prefs[USERID] ?: -1
    }

    override  suspend fun saveToken(token: String) {
        dataStorePrefs.edit { prefs ->
            prefs[TOKEN] = token
        }
    }

    override suspend fun deleteToken(): Boolean {
        dataStorePrefs.edit { prefs ->
            prefs[TOKEN] = ""
        }
        return true
    }

    override suspend fun saveUserId(userId: Int) {
        dataStorePrefs.edit { prefs ->
            prefs[USERID] = userId
        }
    }

    override suspend fun deleteUserId(): Boolean {
        dataStorePrefs.edit { prefs ->
            prefs[USERID] = -1
        }
        return true
    }


    companion object{
        private val TOKEN = stringPreferencesKey("Token")
        private val USERID = intPreferencesKey("UserId")
    }
}