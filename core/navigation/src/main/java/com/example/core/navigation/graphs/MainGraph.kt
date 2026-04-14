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
import com.example.core.navigation.viewModelFactories.FavoritePhotosViewModelFactory
import com.example.core.navigation.viewModelFactories.PhotoDetailViewModelFactory
import com.example.core.navigation.viewModelFactories.PhotosViewModelFactory
import com.example.core.navigation.viewModelFactories.SearchPhotosViewModelFactory
import com.example.feature.favoritephotos.di.FavoritePhotosFeatureProvider
import com.example.feature.favoritephotos.ui.FavoritePhotosScreen
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
            PhotosViewModelFactory(
                getPhotosUseCase = PhotosFeatureProvider.provideGetPhotosUseCase(context),
                darkModeUseCase = PhotosFeatureProvider.provideUpdateDarkModeUseCase(context),
                favoritePhotoUseCase = PhotoDetailFeatureProvider.provideFavoritePhotoUseCase(context)

            )
        }

        PhotosScreen(
            viewModel = backStack.screenViewModel(navController,factory),
            onNavigateToDetail = { navController.navigate("PhotoDetailScreen/${it}")},
            onNavigateToSearch = { navController.navigate("SearchPhotosScreen")},
            onNavigateToFavorite = {navController.navigate("FavoritePhotosScreen")}
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
        val context = LocalContext.current

        val factory = remember {
            SearchPhotosViewModelFactory(
                searchPhotosUseCase = SearchPhotosFeatureProvider.provideSearchPhotosUseCase(context),
                searchHistoryUseCase = SearchPhotosFeatureProvider.provideSearchHistoryUseCase(context)
                )
        }

        SearchPhotosScreen(
            viewModel = backStack.screenViewModel(navController, factory),
            onBackClick = navController::popBackStack,
            onNavigateToDetail = { navController.navigate("PhotoDetailScreen/${it}")},
            )
    }

    composable("FavoritePhotosScreen") { backStack ->
        val context = LocalContext.current

        val factory = remember {
            FavoritePhotosViewModelFactory(
                getFavoritePhotosUseCase = FavoritePhotosFeatureProvider.provide(context),
                favoritePhotoUseCase = PhotoDetailFeatureProvider.provideFavoritePhotoUseCase(context)
            )
        }

        FavoritePhotosScreen(
            viewModel = backStack.screenViewModel(navController, factory),
            onNavigateToDetail = { navController.navigate("PhotoDetailScreen/${it}")},
            onBackClick = navController::popBackStack,
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