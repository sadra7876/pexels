package com.example.core.navigation.navHost

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.core.navigation.graphs.mainGraph

@Composable
fun NavHostContent(
    modifier: Modifier = Modifier,
    initialRout: String = "MainScreens",
    startDestination: String = "PhotosScreen",
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = initialRout,
        enterTransition = {
            slideInHorizontally { it } + fadeIn()
        } ,
        exitTransition = {
            slideOutHorizontally { -it } + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally { -it } + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally { it } + fadeOut()
        }
        ) {
            navigation(route="MainScreens" ,startDestination = startDestination,){
                mainGraph(navController)
            }
    }
}