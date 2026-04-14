package com.example.core.datastore.di

import android.content.Context
import com.example.core.datastore.api.DataStoreManager
import com.example.core.datastore.impl.DataStoreManagerImpl
import com.example.core.datastore.settings.AppSetting
import com.example.core.datastore.settings.AppSettingImpl

object DataStoreProvider {

    @Volatile
    private var appSetting: AppSetting? = null

    @Volatile
    private var dataStoreManager: DataStoreManager? = null

    fun provideAppSetting(context: Context): AppSetting {
        return appSetting ?: synchronized(this) {
            appSetting ?: run {
                val manager = provideDataStoreManager(context)
                AppSettingImpl(manager).also { appSetting = it }
            }
        }
    }

    private fun provideDataStoreManager(context: Context): DataStoreManager {
        return dataStoreManager ?: synchronized(this) {
            dataStoreManager ?: DataStoreManagerImpl(
                context.applicationContext
            ).also { dataStoreManager = it }
        }
    }
}