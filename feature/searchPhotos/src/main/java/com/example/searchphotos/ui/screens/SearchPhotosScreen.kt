package com.example.searchphotos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.common.loader.loaders.PexelImageLoader
import com.example.searchphotos.domain.models.SearchPhotoDN

@Composable
fun SearchPhotosScreen(
    viewModel: SearchPhotosViewModel,
    onBackClick: () -> Unit,
    onPhotoClick: (Long) -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { index ->
            if (index == state.photos.lastIndex) {
                viewModel.loadNextPage()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        SearchTopBar(
            query = state.query,
            onQueryChange = viewModel::onQueryChange,
            onBackClick = onBackClick
        )

        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.photos.isEmpty() -> {
                EmptyState("No results")
            }

            else -> {
                SearchGrid(
                    photos = state.photos,
                    gridState = gridState,
                    isLoadingMore = state.isLoadingMore,
                    onClick = onPhotoClick
                )
            }
        }

    }
}

@Composable
fun SearchTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable { onBackClick() }
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search photos...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@Composable
fun SearchGrid(
    photos: List<SearchPhotoDN>,
    gridState: LazyGridState,
    isLoadingMore: Boolean,
    onClick: (Long) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        state = gridState,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(photos) { photo ->
            PhotoItem(
                photo = photo,
                onClick = { onClick(photo.id) }
            )
        }
        if (isLoadingMore) {
            item(span = { GridItemSpan(99) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun EmptyState(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}


@Composable
private fun PhotoItem(
    photo: SearchPhotoDN,
    onClick: () -> Unit,
) {

    val imageUrl = photo.src.medium

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(photo.width.toFloat() / photo.height)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray.copy(alpha = 0.2f))
            .clickable(onClick = onClick)
    ) {

        PexelImageLoader(
            modifier = Modifier.fillMaxSize(),
            imageUrl = imageUrl,
            contentScale = ContentScale.Crop,
            width = 600,
            height = 600
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.15f)
                        )
                    )
                )
        )
    }
}