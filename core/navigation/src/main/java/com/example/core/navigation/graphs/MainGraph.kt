package com.example.core.navigation.graphs

import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.core.navigation.viewModelFactories.PhotoDetailViewModelFactory
import com.example.core.navigation.viewModelFactories.PhotosViewModelFactory
import com.example.core.navigation.viewModelFactories.SearchPhotosViewModelFactory
import com.example.feature.photodetail.di.PhotoDetailFeatureProvider
import com.example.feature.photodetail.ui.screens.PhotoDetailScreen
import com.example.feature.photos.di.PhotosFeatureProvider
import com.example.feature.photos.ui.screens.PhotosScreen
import com.example.searchphotos.di.SearchPhotosFeatureProvider
import com.example.searchphotos.ui.screens.SearchPhotosScreen


fun NavGraphBuilder.mainGraph(navController: NavController) {

    composable("PhotosScreen") { backStack ->

        val context = LocalContext.current

        val factory = remember {
            PhotosViewModelFactory(PhotosFeatureProvider.provide(context))
        }

        PhotosScreen(
            viewModel = backStack.screenViewModel(navController,factory),
            onNavigateToDetail = { navController.navigate("PhotoDetailScreen/${it}")},
            onNavigateToSearch = { navController.navigate("SearchPhotosScreen")}
        )
    }

    composable(
        route = "PhotoDetailScreen/{postId}",
        enterTransition = {
            scaleIn(initialScale = -2f) + fadeIn()
        },
    ) { backStack ->

        val context = LocalContext.current

        val factory = remember {
            PhotoDetailViewModelFactory(
                PhotoDetailFeatureProvider.provideGetPhotoUseCase(context),
                PhotoDetailFeatureProvider.provideFavoritePhotoUseCase(context)
            )
        }

        val id = backStack.arguments?.getString("postId")?.toLong() ?: 0L

        PhotoDetailScreen (
            viewModel = backStack.screenViewModel(navController, factory),
            photoId = id,
            onBackClick = navController::popBackStack
        )
    }

    composable("SearchPhotosScreen") { backStack ->
        val factory = remember {
            SearchPhotosViewModelFactory(SearchPhotosFeatureProvider.searchPhotosUseCase)
        }

        SearchPhotosScreen(
            viewModel = backStack.screenViewModel(navController, factory),
            onBackClick = navController::popBackStack,
            onPhotoClick = { navController.navigate("PhotoDetailScreen/${it}")},
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