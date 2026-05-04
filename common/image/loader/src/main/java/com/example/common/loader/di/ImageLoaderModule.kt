package com.example.common.loader.di

import com.example.common.loader.loaders.PexelImageLoaderProvider
import org.koin.dsl.module

val imageLoaderModule = module {

    single {
        PexelImageLoaderProvider(get())
    }
}