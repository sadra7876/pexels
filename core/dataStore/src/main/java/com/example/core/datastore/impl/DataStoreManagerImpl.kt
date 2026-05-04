package com.example.core.datastore.impl

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.core.datastore.api.DataStoreManager
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


internal class DataStoreManagerImpl(
    context: Context
) : DataStoreManager {

    val Context.dataStore by preferencesDataStore(name = "settings")

    private val dataStore = context.dataStore

    override suspend fun saveBoolean(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    override fun getBooleanFlow(key: String): Flow<Boolean> {
        return dataStore.data.map {
            it[booleanPreferencesKey(key)] ?: false

        }
    }
}