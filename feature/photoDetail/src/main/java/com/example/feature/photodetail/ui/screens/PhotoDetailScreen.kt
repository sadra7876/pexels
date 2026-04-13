package com.example.feature.photodetail.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.common.loader.loaders.PexelImageLoader
import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.ui.contracts.PhotoDetailUiState
import androidx.core.graphics.toColorInt


@Composable
fun PhotoDetailScreen(
    photoId: Long,
    viewModel: PhotoDetailViewModel,
    onBackClick: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(photoId) {
        viewModel.loadPhoto(photoId)
    }

    when (state) {

        is PhotoDetailUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PhotoDetailUiState.Success -> {


            PhotoContent(
                state as PhotoDetailUiState.Success,
                onBackClick,
                viewModel::toggleFavorite
                )
        }

        is PhotoDetailUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (state as PhotoDetailUiState.Error).message)
            }
        }
    }
}

@Composable
private fun PhotoContent(
    state: PhotoDetailUiState.Success,
    onBackClick: () -> Unit,
    ontToggleFavorite: (state: PhotoDetailUiState.Success) -> Unit
) {
    var animationVisibility by remember { mutableStateOf(false) }
    val bgColor = Color(state.photo.avgColor.toColorInt())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {

        PexelImageLoader(
            modifier = Modifier.fillMaxSize(),
            imageUrl = state.photo.src.large,
            contentScale = ContentScale.Fit,
            onSuccessContent = {
            LaunchedEffect(Unit) {
                animationVisibility = true
            }
            AnimatedVisibility(
                visible = animationVisibility,
                enter = fadeIn(animationSpec = tween(1200))
            ) {
                it()
            }},
            onLoadingContent = {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            },
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {

            Text(
                text = state.photo.photographer,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = state.photo.alt,
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Icon(
            imageVector = if (state.isFavorite)
                Icons.Filled.Favorite
            else
                Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (state.isFavorite) Color.Red else Color.White,
            modifier = Modifier
                .padding(16.dp)
                .size(28.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    ontToggleFavorite(state)
                }
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .size(28.dp)
                .align(Alignment.TopStart)
                .clickable { onBackClick() }
        )
    }
}
