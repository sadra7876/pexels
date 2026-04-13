package com.example.feature.photos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.common.loader.loaders.PexelImageLoader
import com.example.feature.photos.domain.models.PhotoListDN
import com.example.feature.photos.ui.contracts.PhotosUiState


@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel,
    onNavigateToDetail: (Long) -> Unit,
    onNavigateToSearch: () -> Unit,
) {
    val state by viewModel.uiState.collectAsState()
    val gridState = rememberLazyGridState()


    LaunchedEffect(Unit) {

        if (state.photos.isEmpty()) {
            viewModel.loadFirstPage(perPage = 10)
        }

        snapshotFlow {
            gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastIndex ->

            if (lastIndex == state.photos.lastIndex ) {
                viewModel.loadNextPage(perPage = 10)
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(onSearchClick = onNavigateToSearch)
        }
    ) { padding ->

        PhotosGrid(
            state = state,
            gridState = gridState,
            onClick = onNavigateToDetail,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun PhotosGrid(
    state: PhotosUiState,
    gridState: LazyGridState,
    onClick: (Long) -> Unit,
    modifier: Modifier
) {

    if (state.photos.isEmpty() && state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        state = gridState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(state.photos) { photo ->
            PhotoItem(
                photo = photo,
                onClick = {onClick(photo.id)}
                )
        }

        if (state.isLoadingMore) {
            item(span = { GridItemSpan(99) }) {
                Box(
                    modifier = modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Photos") },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
    )
}

@Composable
private fun PhotoItem(
    photo: PhotoListDN,
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