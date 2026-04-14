package com.example.feature.favoritephotos.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.feature.favoritephotos.domain.models.FavoritePhotoDN

@Composable
fun FavoritePhotosScreen(
    viewModel: FavoritePhotosViewModel,
    onNavigateToDetail: (Long) -> Unit,
    onBackClick: () -> Unit
) {

    val favoritePhotos = viewModel.favoritePhotos.collectAsLazyPagingItems()
    val gridState = rememberLazyStaggeredGridState()

    Box(modifier = Modifier.fillMaxSize()){


        FavoriteGrid(
            photos = favoritePhotos,
            gridState = gridState,
            onClick = onNavigateToDetail,
            modifier = Modifier.fillMaxSize(),
            ontToggleFavorite = viewModel::toggleFavorite
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
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


}

@Composable
fun FavoriteGrid(
    photos: LazyPagingItems<FavoritePhotoDN>,
    gridState: LazyStaggeredGridState,
    onClick: (Long) -> Unit,
    modifier: Modifier,
    ontToggleFavorite: (Long) -> Unit
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
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 18.dp
    ) {

        items(photos.itemCount) { index ->
            photos[index]?.let { photo ->
                FavoriteItem(
                    photo = photo,
                    onClick = { onClick(photo.id) },
                    ontToggleFavorite = ontToggleFavorite
                )
            }
        }

        if (loadState.append is LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(
    photo: FavoritePhotoDN,
    onClick: () -> Unit,
    ontToggleFavorite: (Long) -> Unit
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
                .align(Alignment.BottomCenter)
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
        ) {

            Text(
                text = formatDate(photo.likedAt),
                color = Color.White,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }

        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.Red,
            modifier = Modifier
                .padding(8.dp)
                .size(18.dp)
                .align(Alignment.TopEnd)
                .clickable {
                    ontToggleFavorite(photo.id)
                }
        )
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}