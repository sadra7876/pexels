package com.example.common.loader.loaders

import android.content.Context
import coil.ImageLoader
import coil.decode.BitmapFactoryDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest

import coil.size.Size
import coil.transform.Transformation


class PexelImageLoaderProvider(
    private val context: Context
) {
    val imageLoader : ImageLoader = ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCache(
            MemoryCache.Builder(context)
                .maxSizePercent(
                    0.25)
                .build()
        )
        .diskCache(
            DiskCache.Builder()
                .directory(context.cacheDir.resolve(DISK_CACHE_PATH))
                .maxSizePercent(0.2)
                .build()
        ).components{
            add(BitmapFactoryDecoder.Factory())
        }.build()

    private fun internalBuildImageRequest(
        data: Any,
        enableCrossfade: Boolean,
        disableNetworkCache: Boolean = false,
        transformations: List<Transformation> = emptyList(),
        size: Size? = null,
        onError: ((ImageRequest, ErrorResult) -> Unit)? = null,
    ) : ImageRequest {
        return ImageRequest.Builder(context)
            .data(data)
            .crossfade(enableCrossfade)
            .networkCachePolicy(if (disableNetworkCache) CachePolicy.DISABLED else CachePolicy.ENABLED )
            .apply {
                if (transformations.isNotEmpty()) {
                    transformations(transformations)
                }
                onError?.let {
                    listener(onError = it)
                }
                size?.let {
                    size(it)
                }
            }
            .build()
    }

    fun buildImageRequest(
        data: String,
        enableCrossfade: Boolean,
        transformations: List<Transformation>,
        size: Size?
    ) : ImageRequest {
        return internalBuildImageRequest(
            data = data,
            enableCrossfade = enableCrossfade,
            transformations = transformations,
            size = size
        )
    }

    companion object {
        private const val DISK_CACHE_PATH = "image_cache"
    }
}