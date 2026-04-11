package com.example.common.loader.loaders

import android.content.Context
import coil3.ImageLoader
import coil3.decode.BitmapFactoryDecoder
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.size.Size
import coil3.transform.Transformation


class PexelImageLoaderProvider(
    private val context: Context
) {
    val imageLoader : ImageLoader = ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCache(
            MemoryCache.Builder()
                .maxSizePercent(
                    context,
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