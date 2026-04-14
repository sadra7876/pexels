package com.example.core.datastore.settings

import com.example.core.datastore.api.DataStoreManager
import kotlinx.coroutines.flow.Flow

internal class AppSettingImpl(
    private val dataStoreManager : DataStoreManager
) : AppSetting {

    companion object{
        private const val DARK_MODE = "dark_mode"
    }
    override suspend fun updateDarkMode(toDark: Boolean) {
        dataStoreManager.saveBoolean(DARK_MODE, toDark)
    }

    override fun getDarkMode(): Flow<Boolean> {
        return dataStoreManager.getBooleanFlow(DARK_MODE)
    }
}