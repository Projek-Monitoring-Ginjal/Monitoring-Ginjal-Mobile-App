package com.neotelemetrixgdscunand.monitoringginjalapp.domain.data

interface UserPreference {

    suspend fun getToken():String
    suspend fun saveToken(token:String)

    suspend fun deleteToken():Boolean

    suspend fun getUserId():Int

    suspend fun saveUserId(userId:Int)

    suspend fun deleteUserId():Boolean

    companion object{
        const val NAME = "UserPrefs"
    }

}