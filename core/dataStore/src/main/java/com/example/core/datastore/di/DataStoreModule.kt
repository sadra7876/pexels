package com.example.core.datastore.di

import com.example.core.datastore.api.DataStoreManager
import com.example.core.datastore.impl.DataStoreManagerImpl
import com.example.core.datastore.settings.AppSetting
import com.example.core.datastore.settings.AppSettingImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataStoreModule {

    @Binds
    abstract fun provideAppSetting(
        appSettingImpl: AppSettingImpl
    ): AppSetting

    @Binds
    abstract fun bindDataStoreManager(
        dataStoreManager: DataStoreManagerImpl
    ): DataStoreManager

}