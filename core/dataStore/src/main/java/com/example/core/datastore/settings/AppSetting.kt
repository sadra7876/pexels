package com.example.core.datastore.settings

import kotlinx.coroutines.flow.Flow

interface AppSetting {
    suspend fun updateDarkMode(toDark: Boolean)

    fun getDarkMode(): Flow<Boolean>
}