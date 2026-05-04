package com.example.core.datastore.di

import com.example.core.datastore.api.DataStoreManager
import com.example.core.datastore.impl.DataStoreManagerImpl
import com.example.core.datastore.settings.AppSetting
import com.example.core.datastore.settings.AppSettingImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataStoreModule = module {

    singleOf(::AppSettingImpl) { bind<AppSetting>() }
    singleOf(::DataStoreManagerImpl) { bind<DataStoreManager>() }
}