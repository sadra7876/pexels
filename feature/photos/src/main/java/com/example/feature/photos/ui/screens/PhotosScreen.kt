package com.example.feature.photos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.common.loader.loaders.PexelImageLoader
import com.example.feature.photos.domain.models.PhotoListDN

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel,
    onNavigateToDetail: (Long) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToFavorite: () -> Unit,
) {

    val photos = viewModel.photos.collectAsLazyPagingItems()
    val gridState = rememberLazyGridState()

    Scaffold(
        topBar = {
            TopBar(
                onSearchClick = onNavigateToSearch,
                onFavoritesClick = onNavigateToFavorite
            )
        }
    ) { padding ->

        PhotosGrid(
            photos = photos,
            gridState = gridState,
            onClick = onNavigateToDetail,
            modifier = Modifier.padding(padding)
        )
    }
}


@Composable
fun PhotosGrid(
    photos: LazyPagingItems<PhotoListDN>,
    gridState: LazyGridState,
    onClick: (Long) -> Unit,
    modifier: Modifier
) {

    val loadState = photos.loadState

    if (loadState.refresh is LoadState.Loading) {
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

        items(photos.itemCount) { index ->
            photos[index]?.let { photo ->
                PhotoItem(
                    photo = photo,
                    onClick = { onClick(photo.id) }
                )
            }
        }

        if (loadState.append is LoadState.Loading) {
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

        if (loadState.append is LoadState.Error) {
            item(span = { GridItemSpan(99) }) {
                Text("Error loading more...")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit
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

            IconButton(onClick = onFavoritesClick) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites"
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
        if (photo.isFavorite){
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite",
                tint = Color.Red,
                modifier = Modifier
                    .padding(12.dp)
                    .size(18.dp)
                    .align(Alignment.BottomEnd)
            )

        }

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