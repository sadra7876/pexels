package com.example.feature.photos.ui.screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.feature.photos.domain.models.PhotoListDN
import kotlinx.coroutines.withContext
import java.net.URL
import kotlin.math.log

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel,
    onNavigateToRoute: (Long) -> Unit,
) {

    val state by viewModel.uiState.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.loadFirstPage(perPage = 10)
    }

    // pagination
    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastIndex ->
            if (lastIndex == state.photos.lastIndex) {
                viewModel.loadNextPage(perPage = 10)
            }
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        state = gridState,
        modifier = Modifier.fillMaxSize()
    ) {
        Log.d("Photos", "size = ${state.photos.size}")

        items(state.photos) { photo ->
            PhotoItem(
                photo = photo,
                onClick = {onNavigateToRoute(photo.id)}
                )
        }

        if (state.isLoadingMore) {
            item(span = { GridItemSpan(3) }) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun PhotoItem(
    photo: PhotoListDN,
    onClick: () -> Unit,) {

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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
    ){
        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            )
        }
    }

}