package com.example.feature.photodetail.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
            val photo = (state as PhotoDetailUiState.Success).photo

            PhotoContent(photo, onBackClick)
        }

        is PhotoDetailUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (state as PhotoDetailUiState.Error).message)
            }
        }

        else -> Unit
    }
}

@Composable
private fun PhotoContent(
    photo: PhotoDN,
    onBackClick: () -> Unit
) {

    val bgColor = Color(photo.avgColor.toColorInt())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {

        PexelImageLoader(
            modifier = Modifier.fillMaxSize(),
            imageUrl = photo.src.large,
            contentScale = ContentScale.Fit
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
                text = photo.photographer,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = photo.alt ?: "",
                color = Color.White.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // 🔙 Back Button
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

@Composable
private fun PhotoItem(
    photo: PhotoDN
) {

    val imageUrl = photo.src.medium

    PexelImageLoader(
        modifier = Modifier
            .fillMaxWidth(),
        imageUrl = imageUrl,
        contentScale = ContentScale.Fit,
    )

}