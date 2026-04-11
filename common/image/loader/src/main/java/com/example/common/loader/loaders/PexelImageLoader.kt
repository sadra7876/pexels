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
import coil.ImageLoader
import coil.compose.AsyncImagePainter.State
import coil.compose.SubcomposeAsyncImage
import coil.size.Size
import coil.transform.Transformation

val LocalImageLoader  = staticCompositionLocalOf<PexelImageLoaderProvider> { error("No image loader provided") }


@Composable
private fun InternalPexelImageLoader(
    modifier: Modifier,
    contentScale: ContentScale,
    alignment: Alignment,
    imageSource : String,
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
        imageLoaderProvider?.buildImageRequest(
                data = currentSource,
                enableCrossfade = enableCrossfade,
                transformations = transformation,
                size = size
        )
    }

    SubcomposeAsyncImage(
        modifier = modifier,
        contentDescription = "Image",
        imageLoader = imageLoader,
        model = imageRequest,
        contentScale = contentScale,
        alignment = alignment,
//        onSuccess = onSuccess,
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
    transformation: List<Transformation> = emptyList(),
    width: Int? = null,
    height: Int? = null,
    enableCrossfade: Boolean = true,

    ) {
    InternalPexelImageLoader(
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment,
        imageSource = imageUrl,
        transformation = transformation,
        size = if (width != null && height != null) Size(width = width, height = height) else null,
        enableCrossfade = enableCrossfade
    )
}