package com.example.core.datastore.api

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    suspend fun saveBoolean(key: String, value: Boolean)

    fun getBooleanFlow(key: String): Flow<Boolean>

}