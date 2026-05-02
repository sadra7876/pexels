package com.example.core.navigation.graphs

import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.favoritephotos.ui.FavoritePhotosScreen
import com.example.feature.photodetail.ui.screens.PhotoDetailScreen
import com.example.feature.photos.ui.screens.PhotosScreen
import com.example.searchphotos.ui.screens.SearchPhotosScreen


fun NavGraphBuilder.mainGraph(navController: NavController) {

    composable("PhotosScreen") { backStack ->

        PhotosScreen(
            viewModel = backStack.screenViewModel(navController),
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

        val id = backStack.arguments?.getString("postId")?.toLong() ?: 0L

        PhotoDetailScreen (
            viewModel = backStack.screenViewModel(navController),
            photoId = id,
            onBackClick = navController::popBackStack
        )
    }

    composable("SearchPhotosScreen") { backStack ->

        SearchPhotosScreen(
            viewModel = backStack.screenViewModel(navController),
            onBackClick = navController::popBackStack,
            onNavigateToDetail = { navController.navigate("PhotoDetailScreen/${it}")},
            )
    }

    composable("FavoritePhotosScreen") { backStack ->

        FavoritePhotosScreen(
            viewModel = backStack.screenViewModel(navController),
            onNavigateToDetail = { navController.navigate("PhotoDetailScreen/${it}")},
            onBackClick = navController::popBackStack,
            )
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.screenViewModel(
    navController: NavController,
):T {
    val navGraphRoute = destination.route ?: return hiltViewModel<T>()
    val screenEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel<T>( viewModelStoreOwner = screenEntry)
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.graphViewModel(navController: NavController):T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel<T>()
    val graphEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel<T>( viewModelStoreOwner = graphEntry)
}