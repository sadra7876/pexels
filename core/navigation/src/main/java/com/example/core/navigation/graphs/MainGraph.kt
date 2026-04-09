package com.example.core.navigation.graphs

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.navigation.viewModelFactories.PhotoDetailViewModelFactory
import com.example.core.navigation.viewModelFactories.PhotosViewModelFactory
import com.example.feature.photodetail.di.PhotoDetailFeatureProvider
import com.example.feature.photodetail.ui.screens.PhotoDetailScreen
import com.example.feature.photos.di.PhotosFeatureProvider
import com.example.feature.photos.ui.screens.PhotosScreen


fun NavGraphBuilder.mainGraph(navController: NavController) {

    composable("A") { backStack ->

        val factory = remember {
            PhotosViewModelFactory(PhotosFeatureProvider.getPhotosUseCase)
        }

        PhotosScreen(
            viewModel = backStack.screenViewModel(navController,factory),
            onNavigateToRoute = { navController.navigate("B/${it}")}
        )
    }

    composable("B/{postId}") { backStack ->

        val factory = remember {
            PhotoDetailViewModelFactory(PhotoDetailFeatureProvider.getPhotoUseCase)
        }

        val id = backStack.arguments?.getString("postId")?.toLong() ?: 0L

        Log.d("Photos", "id = $id")

        PhotoDetailScreen (
            viewModel = backStack.screenViewModel(navController, factory),
            photoId = id
        )
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.screenViewModel(
    navController: NavController,
    factory: ViewModelProvider.Factory
):T {
    val navGraphRoute = destination.route ?: return viewModel<T>(factory = factory)
    val screenEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel<T>(screenEntry, factory = factory)
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.graphViewModel(navController: NavController):T {
    val navGraphRoute = destination.parent?.route ?: return viewModel<T>()
    val graphEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel<T>(graphEntry)
}