package com.example.common.loader.loaders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
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
    onSuccessContent:  @Composable (content: @Composable () -> Unit) -> Unit = { it() },
    onLoadingContent: @Composable (() -> Unit)? = null,
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
        loading = { onLoadingContent?.invoke() },
        success = { onSuccessContent{
            SubcomposeAsyncImageContent()
        } }
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
    onSuccessContent: @Composable (content: @Composable () -> Unit) -> Unit,
    onLoadingContent: @Composable (() -> Unit)

    ) {
    InternalPexelImageLoader(
        modifier = modifier,
        contentScale = contentScale,
        alignment = alignment,
        imageSource = imageUrl,
        transformation = transformation,
        size = if (width != null && height != null) Size(width = width, height = height) else null,
        enableCrossfade = enableCrossfade,
        onSuccessContent = onSuccessContent,
        onLoadingContent = onLoadingContent
    )
}