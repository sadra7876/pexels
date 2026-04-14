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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.common.loader.loaders.PexelImageLoader
import com.example.core.sharedmodel.dn.PhotoDN
import kotlin.collections.lastIndex

@Composable
fun SearchPhotosScreen(
    viewModel: SearchPhotosViewModel,
    onBackClick: () -> Unit,
    onNavigateToDetail: (Long) -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val gridState = rememberLazyStaggeredGridState()

    fun onPhotoClick(photo: PhotoDN) {
        viewModel.onPhotoClicked(photo)
        onNavigateToDetail(photo.id)
    }

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
            state.query.isEmpty() && state.history.isNotEmpty() -> {
                Column {
                    HistoryHeader(
                        onClearClick = viewModel::clearHistory
                    )

                    HistoryGrid(
                        photos = state.history,
                        onClick = ::onPhotoClick,
                        onDelete = viewModel::deleteHistoryItem
                    )
                }
            }

            state.isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.photos.isEmpty() && state.history.isEmpty() -> {
                EmptyState("No results")
            }

            else -> {
                SearchGrid(
                    photos = state.photos,
                    gridState = gridState,
                    isLoadingMore = state.isLoadingMore,
                    onClick = ::onPhotoClick
                )
            }
        }

    }
}

@Composable
fun HistoryHeader(
    onClearClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "Recent searches",
        )

        Text(
            text = "Clear",
            color = Color.Red,
            modifier = Modifier
                .clickable { onClearClick() }
                .padding(8.dp)
        )
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
    photos: List<PhotoDN>,
    gridState: LazyStaggeredGridState,
    isLoadingMore: Boolean,
    onClick: (PhotoDN) -> Unit
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(120.dp),
        state = gridState,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 18.dp
    ) {

        items(photos.size) { index ->
            photos[index].let { photo ->
                PhotoItem(
                    photo = photo,
                    onClick = { onClick(photo) }
                )
            }
        }
        if (isLoadingMore) {
            item {
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
fun HistoryGrid(
    photos: List<PhotoDN>,
    onClick: (PhotoDN) -> Unit,
    onDelete: (Long) -> Unit
) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 18.dp
    ) {

        items(photos.size) { index ->
            photos[index].let { photo ->

                Box {
                    PhotoItem(
                        photo = photo,
                        onClick = { onClick(photo) }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .align(Alignment.TopCenter)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.6f),
                                        Color.Black.copy(alpha = 0.3f),
                                        Color.Transparent
                                    )
                                )
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.TopEnd)
                                .clickable { onDelete(photo.id) }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun PhotoItem(
    photo: PhotoDN,
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