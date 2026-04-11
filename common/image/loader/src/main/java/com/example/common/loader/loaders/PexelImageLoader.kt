package com.example.common.loader.loaders

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import coil3.ImageLoader
import coil3.compose.AsyncImagePainter.State
import coil3.compose.SubcomposeAsyncImage
import coil3.size.Size
import coil3.transform.Transformation
import kotlin.reflect.typeOf

val LocalImageLoader  = staticCompositionLocalOf<PexelImageLoaderProvider> { error("No image loader provided") }

@Composable
private fun InternalPexelImageLoader(
    modifier: Modifier,
    contentScale: ContentScale,
    alignment: Alignment,
    imageSource : Any,
    transformation: List<Transformation>,
    size: Size? = null,
    enableCrossfade: Boolean = true,
    onSuccess: ((State.Success) -> Unit)? = null,
    onLoadingContent: @Composable (() -> Unit)? = null,
    onErrorContent: @Composable ((onRetry: () -> Unit) -> Unit)? = null,

    ) {

    val context = LocalContext.current
    val isInceptionMode = LocalInspectionMode.current
    val imageLoaderProvider = if (isInceptionMode) null else LocalImageLoader.current
    val imageLoader = imageLoaderProvider?.imageLoader ?: ImageLoader.Builder(context).build()
    var currentSource by remember(imageSource) { mutableStateOf(imageSource) }

    val imageRequest = remember (currentSource){
        when(currentSource){
            is String -> imageLoaderProvider?.buildImageRequest(
                data = currentSource as String,
                enableCrossfade = enableCrossfade,
                transformations = transformation,
                size = size
            )
            else -> throw IllegalArgumentException(" unsupported image type ")
        }
    }

    SubcomposeAsyncImage(
        modifier = modifier,
        contentDescription = "Image",
        imageLoader = imageLoader,
        model = imageRequest,
        contentScale = contentScale,
        alignment = alignment,
        onSuccess = onSuccess,
//        loading = onLoadingContent,
//        onError = onErrorContent
    )
}

@Composable
fun PexelImageLoader(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.FillWidth,
    alignment: Alignment = Alignment.Center,
    transformation: List<Transformation>,
    size: Size? = null,
    enableCrossfade: Boolean = true,

    ) {
    InternalPexelImageLoader(
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment,
        imageSource = imageUrl,
        transformation = transformation,
        size = size,
        enableCrossfade = enableCrossfade
    )
}