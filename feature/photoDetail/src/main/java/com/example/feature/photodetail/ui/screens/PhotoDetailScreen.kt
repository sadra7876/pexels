package com.example.feature.photodetail.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.ui.contracts.PhotoDetailUiState
import kotlinx.coroutines.withContext
import java.net.URL


@Composable
fun PhotoDetailScreen(
    photoId: Long,
    viewModel: PhotoDetailViewModel
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(photoId) {
        viewModel.loadPhoto(photoId)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when (state) {

            is PhotoDetailUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PhotoDetailUiState.Success -> {
                val photo = (state as PhotoDetailUiState.Success).photo

                PhotoItem(
                    photo
                )
            }

            is PhotoDetailUiState.Error -> {
                val message = (state as PhotoDetailUiState.Error).message

                Text(text = message)
            }
        }
    }
}


@Composable
private fun PhotoItem(
    photo: PhotoDN
) {

    val imageUrl = photo.src.medium
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(imageUrl) {
        try {
            bitmap = withContext(kotlinx.coroutines.Dispatchers.IO) {
                val stream = URL(imageUrl).openStream()
                BitmapFactory.decodeStream(stream)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}