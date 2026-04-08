package com.example.pexels.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pexels.di.FirstPageProvider
import com.example.pexels.domain.models.PhotoDN
import com.example.pexels.ui.screens.contracts.PhotosUiState

@Composable
fun PhotosScreen(viewModel: PhotosViewModel) {

    var page by remember { mutableStateOf("") }
    var perPage by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = page,
            onValueChange = { page = it },
            label = { Text("Page") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = perPage,
            onValueChange = { perPage = it },
            label = { Text("Per Page") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val pageInt = page.toIntOrNull() ?: 1
                val perPageInt = perPage.toIntOrNull() ?: 10
                viewModel.loadPhotos(pageInt, perPageInt)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Photos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {

            is PhotosUiState.Idle -> {
                Text("Enter values and click button")
            }

            is PhotosUiState.Loading -> {
                CircularProgressIndicator()
            }

            is PhotosUiState.Success -> {
                val photos = (uiState as PhotosUiState.Success).photos

                LazyColumn {
                    items(photos) { photo ->
                        PhotoItem(photo)
                    }
                }
            }

            is PhotosUiState.Error -> {
                val message = (uiState as PhotosUiState.Error).message
                Text("Error: $message")
            }
        }
    }
}

@Composable
fun PhotoItem(photo: PhotoDN) {

    val imageUrl = photo.src.medium
    var bitmap by remember { mutableStateOf<android.graphics.Bitmap?>(null) }

    LaunchedEffect(imageUrl) {
        try {
            bitmap = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
                val stream = java.net.URL(imageUrl).openStream()
                android.graphics.BitmapFactory.decodeStream(stream)
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
                .height(200.dp)
                .padding(8.dp)
        )
    }
}
@Composable
@Preview
fun x(){
    PhotosScreen(viewModel = FirstPageProvider.photosViewModel)
}