package com.example.feature.photos.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.common.loader.loaders.PexelImageLoader
import com.example.core.sharedmodel.dn.PhotoDN

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel,
    onNavigateToDetail: (Long) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToFavorite: () -> Unit,
) {

    val photos = viewModel.photos.collectAsLazyPagingItems()
    val gridState = rememberLazyStaggeredGridState()
    val isDark by viewModel.isDarkMode.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopBar(
                isDark = isDark,
                onToggleTheme = viewModel::updateDarkMode,
                onSearchClick = onNavigateToSearch,
                onFavoritesClick = onNavigateToFavorite
            )
        }
    ) { padding ->

        PhotosGrid(
            photos = photos,
            gridState = gridState,
            onClick = onNavigateToDetail,
            modifier = Modifier.padding(padding),
            ontToggleFavorite = viewModel::toggleFavorite
        )
    }
}


@Composable
fun PhotosGrid(
    photos: LazyPagingItems<PhotoDN>,
    gridState: LazyStaggeredGridState,
    onClick: (Long) -> Unit,
    modifier: Modifier,
    ontToggleFavorite: (PhotoDN) -> Unit
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

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(120.dp),
        state = gridState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 18.dp
    ) {

        items(photos.itemCount) { index ->
            photos[index]?.let { photo ->
                PhotoItem(
                    photo = photo,
                    onClick = { onClick(photo.id) },
                    ontToggleFavorite = ontToggleFavorite
                )
            }
        }

        if (loadState.append is LoadState.Loading) {
            item{
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
            item {
                Text("Error loading more...")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isDark: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    onSearchClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Photos") },
        actions = {

            AnimatedThemeSwitch(
                isDark = isDark,
                onToggleTheme = onToggleTheme
            )

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
    photo: PhotoDN,
    onClick: () -> Unit,
    ontToggleFavorite: (PhotoDN) -> Unit
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
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.15f),
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 50f
                    )
                )
        ){

        Icon(
            imageVector = if (photo.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (photo.isFavorite) Color.Red else Color.White,
            modifier = Modifier
                .padding(12.dp)
                .size(18.dp)
                .align(Alignment.BottomStart)
                .clickable {
                    ontToggleFavorite(photo)
                }
        )
            }
    }
}

@Composable
fun AnimatedThemeSwitch(
    isDark: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isDark) Color(0xFF1E1E1E) else Color(0xFFFFF3E0)
    )

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(if (isDark) "🌙" else "☀️")

        Spacer(modifier = Modifier.width(8.dp))

        Switch(
            checked = isDark,
            onCheckedChange = onToggleTheme
        )
    }
}